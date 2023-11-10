package org.example;
import org.junit.jupiter.api.Test;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProblemSolvingSystemTest {
    @Test
    void testSolveAndFetchSolvedProblems() {
        ProblemSolvingSystem problemSolvingSystem = ProblemSolvingSystem.getInstance();
        User user = new User("TestUser", "TestDepartment");
        Problem problem = new Problem("TestProblem", "TestTag", "Easy", 10);

        problemSolvingSystem.addUser(user);
        problemSolvingSystem.addProblem(problem);

        problemSolvingSystem.solve(user, problem, 5000); // Assuming it took 5 seconds to solve
        assertTrue(problem.getSolvers().contains(user));

        // Test fetchSolvedProblems
        assertEquals(1, problemSolvingSystem.fetchSolvedProblems(user).size());
        assertEquals(problem, problemSolvingSystem.fetchSolvedProblems(user).get(0));
    }

    @Test
    void testFetchProblemsWithRecommendations() {
        ProblemSolvingSystem problemSolvingSystem = ProblemSolvingSystem.getInstance();
        User user = new User("TestUser", "TestDepartment");
        Problem solvedProblem = new Problem("SolvedProblem", "Tag1", "Medium", 15);
        problemSolvingSystem.addUser(user);
        problemSolvingSystem.addProblem(solvedProblem);

        // Assuming a user solved a problem and now wants recommendations
        List<Problem> recommendedProblems = problemSolvingSystem.getRecommendedProblems(user, solvedProblem, 5);

        // Ensure recommended problems have the same tag as the solved problem
        for (Problem problem : recommendedProblems) {
            assertEquals(solvedProblem.getTag(), problem.getTag());
        }
    }

    // Add more tests for other functionalities such as fetchProblems, displayLeaderboard, etc.

    // Example test for displayLeaderboard
    @Test
    void testDisplayLeaderboard() {
        ProblemSolvingSystem problemSolvingSystem = ProblemSolvingSystem.getInstance();
        User user1 = new User("User1", "Department1");
        User user2 = new User("User2", "Department2");
        Problem problem = new Problem("TestProblem", "TestTag", "Easy", 10);

        problemSolvingSystem.addUser(user1);
        problemSolvingSystem.addUser(user2);
        problemSolvingSystem.addProblem(problem);

        problemSolvingSystem.solve(user1, problem, 5000);
        problemSolvingSystem.solve(user2, problem, 7000);

        // Display leaderboard by user
        problemSolvingSystem.displayLeaderboard(true, 5);

        // Display leaderboard by department
        problemSolvingSystem.displayLeaderboard(false, 5);
    }

}
