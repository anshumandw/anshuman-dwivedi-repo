package com.crio.shorturl;

import java.util.HashMap;

class XUrlImpl implements XUrl {

    public HashMap<String, String> map = new HashMap<>();
    public HashMap<String, String> reverseMap = new HashMap<>();
    public HashMap<String, Integer> hitCount = new HashMap<>();

    private String alphanumericString() {
        String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvwxyz";

        StringBuilder sb = new StringBuilder(9);

        for(int i=0; i<9; i++ ) {
            int index = (int) (s.length() * Math.random());
            sb.append(s.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public String registerNewUrl(String longUrl) {
        String shortUrl = "http://short.url/" + alphanumericString();
        if(map.containsKey(longUrl)) {
            return map.get(longUrl);
        } else {
            reverseMap.put(shortUrl, longUrl);
            map.put(longUrl, shortUrl);
        }
        return map.get(longUrl);
    }

    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {
        if(reverseMap.containsKey(shortUrl)) {
            return null;
        } else {
            map.put(longUrl, shortUrl);
            reverseMap.put(shortUrl, longUrl);
        }
        return map.get(longUrl);
    }

    @Override
    public String getUrl(String shortUrl) {
        String longUrl = reverseMap.get(shortUrl);
        if(hitCount.containsKey(longUrl)) {
            hitCount.put(longUrl, hitCount.get(longUrl) + 1);
        } else {
            hitCount.put(longUrl, 1);
        }
        return longUrl;
    }

    @Override
    public Integer getHitCount(String longUrl) {
        Integer answer = 0;
        if(hitCount.containsKey(longUrl)) {
            answer = hitCount.get(longUrl);
        }
        return answer;
    }

    @Override
    public String delete(String longUrl) {
        String shortUrl = map.get(longUrl);
        return reverseMap.remove(shortUrl);
    }

}