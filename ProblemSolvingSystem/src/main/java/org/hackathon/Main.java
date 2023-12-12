package org.hackathon;


import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class User {

    private final String name;
    private final String department;
    private final Set<Problem> solvedProblems;

    public User(String name, String department) {
        this.name = name;
        this.department = department;
        this.solvedProblems = new HashSet<>();
    }

    // Getter methods for name and department

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public void addInMySolvedSet(Problem problem) {
        solvedProblems.add(problem);
    }

    public Set<Problem> getSolvedProblems() {
        return solvedProblems;
    }
}

class Problem {

    private final String name;
    private final String tag;
    private final String difficultyLevel;
    private final int score;
    private final Set<User> solvers;

    private final Map<User, Long> userTimeMap;

    public Problem(String name, String tag, String difficultyLevel, int score) {
        this.name = name;
        this.tag = tag;
        this.difficultyLevel = difficultyLevel;
        this.score = score;
        this.solvers = new HashSet<>();
        this.userTimeMap = new HashMap<>();
    }

    // Getter methods for description, tag, difficultyLevel, and score

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getScore() {
        return score;
    }

    // Method to add a solver for this problem
    public void addSolver(User user) {
        solvers.add(user);
    }

    // Getter method to retrieve solvers
    public Set<User> getSolvers() {
        return solvers;
    }

    public void recordTime(User user, long timeTaken) {
        userTimeMap.put(user, timeTaken);
    }

    public double getAverageTime() {
        if (userTimeMap.isEmpty()) {
            return 0.0; // Return 0 if no users have solved the problem
        }

        long totalTime = userTimeMap.values().stream().mapToLong(Long::longValue).sum();
        return (double) totalTime / userTimeMap.size();
    }
}

class ProblemSolvingSystem {

    private final List<User> users;
    private final List<Problem> problems;

    private final ReadWriteLock lock = new ReentrantReadWriteLock();


    private volatile static ProblemSolvingSystem uniqueInstance = null;

    public static ProblemSolvingSystem getInstance() {
        if (uniqueInstance == null) {
            synchronized (ProblemSolvingSystem.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new ProblemSolvingSystem();
                }
            }
        }
        return uniqueInstance;
    }

    private ProblemSolvingSystem() {
        this.users = new ArrayList<>();
        this.problems = new ArrayList<>();
    }

    // Function to register a user
    public void addUser(User user) {
        lock.writeLock().lock();
        try {
            if (users.stream().anyMatch(u -> u.getName().equals(user.getName()))) {
                throw new IllegalArgumentException("User already exists");
            }
            users.add(user);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Function to add a problem to the library
    public void addProblem(Problem problem) {
        lock.writeLock().lock();
        try {
            if (problems.stream().anyMatch(p -> p.getName().equals(problem.getName()))) {
                throw new IllegalArgumentException("Problem already exists");
            }
            problems.add(problem);
        } finally {
            lock.writeLock().unlock();
        }
    }

    // Function to fetch the list of problems based on filtering and sorting criteria
    public List<Problem> fetchProblems(String difficultyLevel, String tag, boolean sortByScore) {
        lock.readLock().lock();
        try {
            List<Problem> filteredProblems = new ArrayList<>();

            // Iterate through all problems to filter based on criteria
            for (Problem problem : problems) {
                boolean difficultyMatch =
                    difficultyLevel == null || problem.getDifficultyLevel().equals(difficultyLevel);
                boolean tagMatch = tag == null || problem.getTag().equals(tag);

                if (difficultyMatch && tagMatch) {
                    // Include the problem if it matches the specified criteria
                    filteredProblems.add(problem);

                    // Display the number of users who have solved the problem
                    Set<User> solvers = problem.getSolvers();
                    int numberOfSolvers = solvers.size();
                    System.out.println(
                        "Problem: " + problem.getName() + " Difficulty: "
                            + problem.getDifficultyLevel()
                            + " Score: " + problem.getScore());
                    // Display the average time taken to solve the problem
                    double averageTimeTaken = problem.getAverageTime();
                    System.out.println(
                        " Solvers: " + numberOfSolvers + " Average Time Taken: " + averageTimeTaken
                            + "(seconds)");
                    System.out.println();
                }
            }

            if (sortByScore) {
                // Sort the list in descending order of score
                filteredProblems.sort(Comparator.comparingInt(Problem::getScore).reversed());
            }

            return filteredProblems;
        } finally {
            lock.readLock().unlock();
        }

    }

    // Function to mark a problem as solved by a user
    public void solve(User user, Problem problem, long timeTaken) {
        lock.writeLock().lock();
        try {
            // Add the user to the list of solvers for the problem
            user.addInMySolvedSet(problem);
            problem.recordTime(user, timeTaken);
            problem.addSolver(user);
        } finally {
            lock.writeLock().unlock();
        }

        // Return next 5 recommended problems for the user
    }

    // Function to fetch the list of solved problems for a user
    public List<Problem> fetchSolvedProblems(User user) {
        lock.readLock().lock();
        try {
            // Implement logic to retrieve the list of problems solved by the user

            List<Problem> solvedProblems = new ArrayList<>();

            for (Problem problem : problems) {
                Set<User> solvers = problem.getSolvers();
                if (solvers.contains(user)) {
                    solvedProblems.add(problem);
                }
            }

            // Return the list of solved problems
            return solvedProblems;
        } finally {
            lock.readLock().unlock();
        }

    }

    // Function to display the leaderboard
    public void displayLeaderboard(boolean byUser, int topN) {
        lock.readLock().lock();
        try {
            if (byUser) {
                System.out.println("Leaderboard by User:");

                // Sort users by their total score in reverse order
                users.sort((o1, o2) -> {
                    int totalScore1 = calculateTotalScore(o1.getSolvedProblems());
                    int totalScore2 = calculateTotalScore(o2.getSolvedProblems());
                    return Integer.compare(totalScore2, totalScore1);
                });

                // Display the top 'n' contestants with maximum score and the problems they solved
                for (int i = 0; i < Math.min(topN, users.size()); i++) {
                    User user = users.get(i);
                    int totalScore = calculateTotalScore(user.getSolvedProblems());

                    System.out.println(
                        "Rank " + (i + 1) + ": " + user.getName() + " - Total Score: "
                            + totalScore);

                    // Display the problems solved by the user
                    System.out.println("Solved Problems:");
                    for (Problem problem : user.getSolvedProblems()) {
                        System.out.println("- " + problem.getName());
                    }

                    System.out.println();
                }
            } else {
                System.out.println("Leaderboard by Department:");

                // Map to store the total score for each department
                Map<String, Integer> departmentScores = new HashMap<>();

                // Calculate the total score for each department
                for (User user : users) {
                    departmentScores.put(user.getDepartment(),
                        departmentScores.getOrDefault(user.getDepartment(), 0)
                            + calculateTotalScore(
                            user.getSolvedProblems()));
                }

                // Sort departments by total score
                List<Map.Entry<String, Integer>> sortedDepartments = new ArrayList<>(
                    departmentScores.entrySet());
                sortedDepartments.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

                // Display the top 'n' departments with the best scores
                for (int i = 0; i < Math.min(topN, sortedDepartments.size()); i++) {
                    Map.Entry<String, Integer> entry = sortedDepartments.get(i);
                    System.out.println(
                        "Rank " + (i + 1) + ": Department " + entry.getKey() + " - Total Score: "
                            + entry.getValue());
                }
            }
        } finally {
            lock.readLock().unlock();
        }
    }

    // Helper method to calculate the total score for a user
    private int calculateTotalScore(Set<Problem> solvedProblems) {
        return solvedProblems.stream().mapToInt(Problem::getScore).sum();
    }

    public List<Problem> getRecommendedProblems(User user, Problem solvedProblem,
        int numberOfRecommendations) {
        List<Problem> recommendedProblems = new ArrayList<>();

        String solvedProblemTag = solvedProblem.getTag();

        // TODO: Implement logic to retrieve recommended problems

        for (Problem problem : problems) {
            if (!problem.equals(solvedProblem) && problem.getTag().equals(solvedProblemTag)) {
                recommendedProblems.add(problem);
                if (recommendedProblems.size() == numberOfRecommendations) {
                    break;
                }
            }
        }

        System.out.println(
            "Recommended Problems for " + user.getName() + ":" + recommendedProblems);

        return recommendedProblems;
    }

    // Additional method to clean up or reset the singleton
    public static void resetInstance() {
        uniqueInstance = null;
    }

    public void clearUsers() {
        this.users.clear();
    }
}

public class Main {

    public static void main(String[] args) {
        // get the timestamp
        long startTime = System.currentTimeMillis();
        // Instantiate the backend
        ProblemSolvingSystem problemSolvingSystem = ProblemSolvingSystem.getInstance();

        // Create users and problems
        User user1 = new User("Swapnil", "IT");
        User user2 = new User("John", "Finance");
        User user3 = new User("Alice", "HR");
        User user4 = new User("Alice", "HR");

        Problem problem1 = new Problem("InsertIntoDoublyLinkedList", "Tag1", "Easy", 10);
        Problem problem2 = new Problem("MergeSort", "Tag2", "Medium", 20);
        Problem problem3 = new Problem("LRUCache", "Tag1", "Hard", 30);

        // Register users and add problems to the library
        try {
            problemSolvingSystem.addUser(user1);
            problemSolvingSystem.addUser(user2);
            problemSolvingSystem.addUser(user3);
            problemSolvingSystem.addUser(user4);
        } catch (IllegalArgumentException e) {
            System.out.println("Failed adding user: " + e.getMessage());
        }

        problemSolvingSystem.addProblem(problem1);
        problemSolvingSystem.addProblem(problem2);
        problemSolvingSystem.addProblem(problem3);

        // Solve problems
        problemSolvingSystem.solve(user1, problem1, 100);
        problemSolvingSystem.solve(user2, problem2, 200);
        problemSolvingSystem.solve(user3, problem1, 300);

        // Fetch and display solved problems for a user
        List<Problem> solvedProblems = problemSolvingSystem.fetchSolvedProblems(user1);
        System.out.println("Solved Problems for " + user1.getName() + ":");
        for (Problem problem : solvedProblems) {
            System.out.println("- " + problem.getName());
        }

        // Fetch and display problems based on filtering and sorting criteria
        List<Problem> filteredProblems = problemSolvingSystem.fetchProblems("Easy", "Tag1", true);
        System.out.println("Filtered Problems:");
        for (Problem problem : filteredProblems) {
            System.out.println("- " + problem.getName());
        }

        // Display leaderboard
        // Currently, giving all the users and departments which we can extend to top N
        System.out.println();
        problemSolvingSystem.displayLeaderboard(true, Integer.MAX_VALUE);
        System.out.println();
        problemSolvingSystem.displayLeaderboard(false, Integer.MAX_VALUE);

        // get the timestamp
        long endTime = System.currentTimeMillis();
        // calculate elapsed time
        long elapsedTime = endTime - startTime;
        System.out.println("Total elapsed time: " + elapsedTime + " milliseconds");
    }
}
