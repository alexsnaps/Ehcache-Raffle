<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="net.sf.ehcache.CacheManager" %>
<%@ page import="net.sf.ehcache.Cache" %>
<%@ page import="org.terracotta.ehcache.raffle.CsvLoader" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Random" %>
<%@ page import="org.terracotta.ehcache.raffle.model.Participant" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>

<%
    CacheManager cacheManager = CacheManager.getInstance();
    Cache cache = cacheManager.getCache("winners");
    int i = 0;
    cache.registerCacheLoader(new CsvLoader(new File(System.getProperty("user.home") + "/participants.csv")));
    while (i < 250) {
        cache.load(i++);
    }

    List keys = cache.getKeys();
    Collections.shuffle(keys);
    int participants = keys.size();
    String gift = null;
    Participant winner = null;

    Random random = new Random();
    if ("iPod".equalsIgnoreCase(request.getParameter("type"))) {
        gift = "iPod Shuffle";
        winner = (Participant)cache.get(keys.get(random.nextInt(participants))).getObjectValue();
    }

    String message = "";
    if (winner != null) {
        message = "The winner of the <em>" + gift + "</em> is <br /><em id=\"winner\"></em>";
    }

%>

<html>
<head>
    <title>Winners</title>
    <link rel="stylesheet" href="../style.css" TYPE="text/css" media="screen"/>
    <style type="text/css">
        h2 {
            margin: 150px 0 200px 0;
        }

        h2 em {
            color: black;
        }

    </style>
    <script type="text/javascript"><!--

    var dots = 0;

    setInterval('showWinner()', 500);
    function showWinner()
    {
        var winner = document.getElementById("winner");
        if (winner) {
            if (dots++ < 3) {
                var prefix = "";
                var d = dots;
                while (d-- > 0) {
                    prefix += ".";
                }
                winner.innerHTML = prefix;
            } else {
                winner.innerHTML = "... <%= winner %> !";
            }
        }
    }
    // --></script>
</head>
<body>
<img src="../logo.gif" alt="logo" width="294" height="60"/>
<hr/>

<h2><%= message %>
</h2>
<hr/>

<h3>Get winner for:</h3>

<form>
    <input type="hidden" name="type" value="ipod"/>
    <input type="submit" value="iPod shuffle"/>
</form>

<%= participants %> registered participants
</body>
</html>
