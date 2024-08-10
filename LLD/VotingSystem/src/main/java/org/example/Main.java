package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
// voting is done and now we want to find the winner?
class Vote {

    public final String candidateName;
    public final int preference;

    Vote(String candidateName, int preference) {
        this.candidateName = candidateName;
        this.preference = preference;
    }
}

class VotingSystem {

    private int totalVotes = 0;
    private List<Vote> votes = new ArrayList<>();

    VotingSystem() {

    }

    List<String> findWinner(List<Vote> votes) {
        // process the votes and then get the map candidate name(assuming key) , count
        List<String> ans = new ArrayList<>();

        Map<String, Integer> voteMap = new HashMap<>();

        for (var vote : votes) {
            String name = vote.candidateName;
            int preference = vote.preference;
            voteMap.put(name, voteMap.getOrDefault(name, 0) + preference);
        }

        List<Pair<String, Integer>> voteCount = new ArrayList<>();
        for (String name : voteMap.keySet()) {
            Integer count = voteMap.get(name);
            voteCount.add(new Pair<>(name, count));
        }

        Collections.sort(voteCount, new Comparator<Pair<String, Integer>>() {
            @Override
            public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {
                if (o1.voteCount > o2.voteCount) {
                    return -1;
                } else if (o1.voteCount.equals(o2.voteCount)) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        // getting the candidates
        for (var vc : voteCount) {
            ans.add(vc.candidateName);
        }

        return ans;
    }

    public void processVote(String candidateName, Integer preference) {
        Vote vote = new Vote(candidateName, preference);
        ++totalVotes;
        if (vote.preference < 1 || vote.preference > 3) {
            System.out.println("Please enter valid preference");
        }
        votes.add(vote);
    }

    public List<Vote> getVotes() {
        return votes;
    }
}

class Pair<S, I extends Number> {

    public String candidateName;
    Integer voteCount;

    Pair(String candidateName, Integer voteCount) {
        this.candidateName = candidateName;
        this.voteCount = voteCount;
    }

    String getCandidateName() {
        return candidateName;
    }

    int getVoteCount() {
        return voteCount;
    }
}

public class Main {


    public static void main(String[] args) {
        // voting...

        VotingSystem vs = new VotingSystem();

        // voter 1
        // process votes

        vs.processVote("A", 3);
        vs.processVote("B", 2);
        vs.processVote("C", 1);

        // voter 2
        vs.processVote("C", 2);
        vs.processVote("B", 3);
        vs.processVote("A", 1);

        // voter 3

        vs.processVote("C", 2);
        vs.processVote("D", 3);
        vs.processVote("A", 1);

        List<Vote> votes = vs.getVotes();

        List<String> winners = vs.findWinner(votes);

        for (String candidateName : winners) {
            System.out.print(candidateName + " ");
        }

    }
}