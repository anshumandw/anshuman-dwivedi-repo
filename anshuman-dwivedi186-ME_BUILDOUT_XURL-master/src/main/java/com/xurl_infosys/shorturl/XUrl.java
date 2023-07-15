package com.xurl_infosys.shorturl;

public interface XUrl {

  String registerNewUrl(String longUrl);

  String registerNewUrl(String longUrl, String shortUrl);

  String getUrl(String shortUrl);

  Integer getHitCount(String longUrl);

  String delete(String longUrl);

}

