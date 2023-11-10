# PhonePe Hackathon Backend Design

## Mandatory Functionalities

1. The system can assume a fixed set of problems. Capability to add problems to the Questions Library is optional.
2. Contestants should be able to register themselves with their name and department name.
3. A problem should have attributes like description, tag, difficulty level (easy, medium, hard), score.
4. Contestants should be able to filter problems based on difficulty level or tags and sort them based on score (design should be extensible to other attributes).
5. A contestant should be able to solve a problem as well as get the list of problems solved by him/her.
6. A contestant should be able to see the number of users that have solved a given problem and average time taken to solve that problem.
7. Scoring strategy for a problem could simply be to award the score assigned for the problem or could be something different like a combination of score and time.
8. Display the leaderboard [Important] which can have 2 variants -
    - A. Display the top ‘n’ contestants with the maximum score and the problems that they have solved.
    - B. Display the top ‘n’ departments which have the best scores (Score of a department will be the sum of scores of all its employees).

## Extension Problem

- On solving a problem, users should get a recommendation of the top 5 problems based on relevance.
- The recommendation strategy for problems could simply be similar tags or extensible to include other factors like the number of users who have solved a particular problem or a combination of factors (Design should be extensible).
- Users should be able to get curations like Top 10 most liked problems of a certain tag.

## Capabilities

### Functions to be Supported:

1. Registering a user - `addUser()`
2. Fetch the list of problems - `fetchProblems()`
    - Should take as input filtering and sorting criteria and return all matching problems in the right order.
    - The result should contain problem attributes like name, tag, difficulty level, score, etc.
    - Should also display the number of users who have solved a problem and the average time taken for that problem.
3. Solve a problem - `solve()`
    - Marks a problem as solved.
    - For the extension problem, this function should return the next 5 recommended problems.
4. Fetch solved problems for a user - `fetchSolvedProblems()`
    - Fetch the list of solved problems for a user.
5. Display leaderboard - `displayLeaderboard()`
    - Could take as input a flag for either user or department and return the respective leaderboard.

## Guidelines

1. Store the data in-memory using a language-specific data-structure.
2. Implement clear separation between your data layers and service layers.
3. Simple and basic functions are expected as an entry point - no marks for providing a fancy RESTful API or framework implementation.
4. Because this is a machine coding round, heavy focus would be given on data modeling, code quality, etc. Candidates should not focus too much time on an algo which compromises with implementation time.

## Expectations

1. Code should cover all the mandatory functionalities explained above.
2. Code should be executable and clean.
3. Code should be properly refactored, and exceptions should be gracefully handled.
4. Appropriate errors should be displayed on the console.

## Evaluation Criteria

1. Code should be working.
2. Code readability and testability.
3. Separation Of Concerns.
4. Abstraction.
5. Object-Oriented concepts.
6. Language proficiency.
7. Scalability.
8. Test Coverage (Bonus Points).

**Execution Time Limit:** 3 seconds (Java)

**Memory Limit:** 1 GB

---