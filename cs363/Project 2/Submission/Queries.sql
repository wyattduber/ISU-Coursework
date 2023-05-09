USE Project2;

SELECT DISTINCT retweet_count,textbody,name,category,subcategory FROM tweets WHERE month=1 AND year=2016 ORDER BY retweet_count DESC LIMIT 5;

SELECT u.name,u.state FROM User JOIN hashtag h ON h.hashtag = u.hashtag WHERE h.hashtag='HappyNewYear' OR h.hashtag='NewYear' OR h.hashtag='NewYears' OR h.hashtag='NewYearsDay' ORDER BY u.followers DESC LIMIT 12;
SELECT name,party,followers FROM User WHERE subcategory='GOP' OR subcategory='Democrat' ORDER BY followers DESC LIMIT 5;
SELECT DISTINCT hashtag,state FROM Hashtag WHERE state='Ohio' OR state='Alaska' OR state='Alabama' AND month='1' AND year='2016'
