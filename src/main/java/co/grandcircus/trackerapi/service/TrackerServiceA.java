package co.grandcircus.trackerapi.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;
import co.grandcircus.trackerapi.model.CountPair;

@Service
public class TrackerServiceA implements TrackerService {

    static public List<CountPair> countPairList = new ArrayList<>();
    private List<String> tokensInOrder = new ArrayList<>();

    @Override
    public void add(String token) {

        tokensInOrder.add(token);
        boolean found = false;
        for (CountPair countPair : countPairList) {
            if (countPair.getToken().equals(token)) {
                countPair.setCount(countPair.getCount() + 1);
                found = true;
            }
        }
        if (found == false) {
            countPairList.add(new CountPair(token, 1));
        }

    }


    @Override
    public void reset() {
        countPairList.clear();
        tokensInOrder.clear();
    }

    @Override
    public int getTotalCount() {

        return countPairList.size();
    }

    @Override
    public boolean getTokenExists(String token) {
        boolean found = false;
        for (CountPair countPair : countPairList) {
            if (countPair.getToken().equals(token)) {
                found = true;
            }
        }
        return found;
    }

    @Override
    public int getTokenCount(String token) {
        int countPairCount = 0;
        for (CountPair countPair : countPairList) {
            if (countPair.getToken().equals(token)) {
                countPairCount = countPair.getCount();
            }
        }
        return countPairCount;
    }

    @Override
    public String getLatest() {
        if (tokensInOrder.size() >= 1) {
            return tokensInOrder.get(tokensInOrder.size() - 1);
        }
        return "";
    }

    @Override
    public CountPair getTop() {
        if (countPairList.size() == 0) {
            return new CountPair("", 0);
        } else {
            int highest = 0;
            CountPair topCount = new CountPair();
            for (CountPair countPair : countPairList) {
                if (countPair.getCount() > highest) {
                    topCount = countPair;
                    highest = countPair.getCount();
                }
            }
            return topCount;
        }

    }

    @Override
    public List<String> getLatest5() {
        List<String> lastFiveTokens = new ArrayList<>();

        if (tokensInOrder.size() >= 5) {
            for (int i = (tokensInOrder.size() - 1); i > (tokensInOrder.size() - 5); i--) {
                lastFiveTokens.add(tokensInOrder.get(i));
            }
            return lastFiveTokens;
        } else {
            return tokensInOrder;
        }


    }

    @Override
    public List<CountPair> getTop5() {
        // List<CountPair> top5List = new ArrayList<>();

        // if (countPairList.size() == 0) {
        // top5List.add(new CountPair("", 0));
        // return top5List;
        // } else if (countPairList.size() <= 5) {
        // return countPairList;
        // } else {
        // Map<String, Integer> top5 = new TreeMap<>();
        // for (CountPair countPair : countPairList) {

        // }
        // return top5List;
        // }

        return null;
    }

}
