USE Project2;

LOAD DATA INFILE 'C:\Users\wyatt\OneDrive - Iowa State University\Desktop\School\Spring 2021\COMS 363\Project 2\dataCSV\tweets.txt'
INTO TABLE tweet
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(tid,textbody,retweet_count,retweeted,@col5,@col6)
set day_posted= day(str_to_date(@col5, '%Y-%m-%d %H:%i:%s')),
month_posted= month(str_to_date(@col5, '%Y-%m-%d %H:%i:%s')),
year_posted= year(str_to_date(@col5, '%Y-%m-%d %H:%i:%s'));

LOAD DATA INFILE 'C:\Users\wyatt\OneDrive - Iowa State University\Desktop\School\Spring 2021\COMS 363\Project 2\dataCSV\mentioned.txt'
INTO TABLE tweet
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(tid,screen_name);

LOAD DATA INFILE 'C:\Users\wyatt\OneDrive - Iowa State University\Desktop\School\Spring 2021\COMS 363\Project 2\dataCSV\tagged.txt'
INTO TABLE Hashtag
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(tid,hashtagname);

LOAD DATA INFILE 'C:\Users\wyatt\OneDrive - Iowa State University\Desktop\School\Spring 2021\COMS 363\Project 2\dataCSV\urlused.txt'
INTO TABLE URL
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(tid,url);

LOAD DATA INFILE 'C:\Users\wyatt\OneDrive - Iowa State University\Desktop\School\Spring 2021\COMS 363\Project 2\dataCSV\user.txt'
INTO TABLE User
FIELDS TERMINATED BY ';' OPTIONALLY ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 LINES
(screen_name,name,sub_category,category,ofstate,numFollowers,numFollowing);
