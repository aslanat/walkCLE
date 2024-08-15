BEGIN TRANSACTION;

INSERT INTO users (username,profile_picture,password_hash,role) VALUES ('user','','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,profile_picture,password_hash,role) VALUES ('admin','','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');


INSERT INTO quests(quest_name, quest_description, quest_category)
VALUES	( 'Defend the Land', 'Calling all sports fans, come see why CLE has the most devoted fans in the Land and hit interesting spots along the way.', 200),
		( 'Cleveland Rocks!', 'Home of Rock & Roll, the music scene in Cleveland strikes a chord with fans and is the key for an exciting experience around town.', 300),
		( 'ArtsyCLE', 'Come explore what makes Cleveland light up, from museums and gardens to the beautiful Playhouse Square, there is something for everyone!', 100);


INSERT INTO location (location_pic, location_link, location_name, address,  description, wheelchair_accessible, kid_friendly, public_restroom, cost, quest_category, latitude, longitude)
VALUES  ('https://3.bp.blogspot.com/-pFqtQbCAMZQ/UKHDFTgaHsI/AAAAAAAAABc/APoZ82HVU-4/s1600/playhouse+square+1.jpg','https://www.playhousesquare.org/','Playhouse Square', '1501 Euclid Ave #200 Cleveland, OH 44115', 'A theater district in downtown Cleveland. The largest performing arts center in the US outside of NYC.', true, true, true, 'Cost varies', 100, 41.501060, -81.681780 ),
		('https://th.bing.com/th?id=OLC.w821QKaHEy9Fvw480x360&w=212&h=140&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','https://www.clevelandart.org/', 'Cleveland Museum of Art', '11150 East Boulevard, Cleveland, OH 44106', 'The museum is internationally renowned for its substantial holdings of Asian and Egyptian art and houses a diverse permanent collection of more than 61,000 works of art from around the world.', true, true, true, 'Free to public. Special exhibits may cost extra.', 100, 41.509520, -81.612180),
		('https://th.bing.com/th?id=OLC.oBr4BPYHmvPGrw480x360&w=186&h=140&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','https://holdenfg.org/cleveland-botanical-garden/gardens-and-attractions/','Botanical Gardens', '11030 East Boulevard Cleveland, OH 44106',  'Garden guests enjoy delightful surprises, quiet moments, and lots of fun as they explore 10 acres of beautiful outdoor gardens and the 18,000-square-foot Eleanor Armstrong Smith Glasshouse.', true, true, true, 'Ticket prices start at $13.', 100, 41.451630, -81.701610),
		('https://th.bing.com/th?id=OIP.zLtbUViJFgA5Ar6nSTnBeQHaFl&w=288&h=216&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','https://clevelandbrownsstadium.com/', 'First Energy Stadium', '100 Alfred Lerner Way, Cleveland, OH 44114',  'It is the home field of the Cleveland Browns of the National Football League, and serves as a venue for other events such as college and high school football, soccer, hockey, and concerts.', true, true, true, 'Cost varies based on event.', 200, 41.505900, -81.699370),
		('https://th.bing.com/th?id=OIP.FCy_Vbc4N_LijDzgYYTyvAHaFj&w=288&h=216&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','https://www.mlb.com/guardians/ballpark',' Progressive Stadium', '2401 Ontario St, Cleveland, OH 44115',  'Progressive Field is a baseball stadium located in the downtown area of Cleveland, Ohio, United States. It is the home of the Cleveland Guardians of Major League Baseball', true, true, true, 'Cost varies based on event.', 200, 41.494930, -81.685257),
		('https://th.bing.com/th?id=OIP.oNsqDgI8QlKhuBhjTMvClAHaFj&w=288&h=216&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','https://www.rocketmortgagefieldhouse.com/','Rocket Mortgage FieldHouse', '1 Center Court, Cleveland, OH 44115',  'Rocket Mortgage FieldHouse is a multi-purpose arena in Cleveland, Ohio. The building is the home of the Cleveland Cavaliers of the National Basketball Association and the Cleveland Monsters of the American Hockey League.', true, true, true, 'Cost varies based on event.', 200, 41.496891, -81.689636),
		('https://th.bing.com/th/id/OIP.tavzR2Dj33eUojj2URlsJwHaEI?w=276&h=180&c=7&r=0&o=5&dpr=1.5&pid=1.7','https://www.houseofblues.com/cleveland','House of Blues', '308 Euclid Ave, Cleveland, OH 44114',  'Rock- & blues-themed restaurant & bar serving Southern-inspired dishes, plus live music.', true, true, true, 'Cost varies based on event.', 300, 41.499119, -81.690804 ),
		('https://th.bing.com/th/id/OIP.4slrOWcni9fqFZ_RRJ2JwQHaHa?w=194&h=194&c=7&r=0&o=5&dpr=1.5&pid=1.7','https://www.agoracleveland.com/','The Agora', '5000 Euclid Ave #101, Cleveland, OH 44103',  'The Agora Theatre and Ballroom is a music venue located in Cleveland, Ohio.', true, true, true, 'Cost varies based on event', 300, 41.503368, -81.653702),
		('https://th.bing.com/th?id=OIP.Ujy9ZpQ9gV0GR5LFIqrXSgAAAA&w=333&h=187&c=8&rs=1&qlt=90&o=6&dpr=1.5&pid=3.1&rm=2','https://rockhall.com/','Rock & Roll Hall Of Fame', '1100 E 9th St, Cleveland, OH 44114', 'The museum documents the history of rock music and the artists, producers, engineers, and other notable figures and personnel who have influenced its development.', true, true, true, 'Tickets are $35 for 12+, $25 for 6-12, kids under 5 are free.', 300, 41.508518, -81.695503);

-- INSERT INTO user_quest(badge_id)
-- values (1001),
-- 		(2002),
-- 		(3003);


--INSERT INTO questions(question_text, correct_answer, quest_category)
--values 	( '"What number was Jim Brown?" (Found on Jim Brown Statue)', '32', 200),
--		( '"What date did Eddie Murray hit his game-winning single in the 11th inning of Game Three of the 1995 World Series? MM/DD/YYYY" (Found on Greatest Moments in Progressive Field History marker)', '10/24/1995', 200 ),
--		( '"When was Rocket Mortgage Fieldhouse renamed from Quicken Loans Arena? MM/DD/YYYY"', '04/09/2019', 200),
--		( '	"When was the first rock and roll concert held, hosted by DJ Alan Freed, who coined the term rock and roll on his radio show "The Moon Dog House"? MM/DD/YYYY" (Found on Ohio Historical marker)', '03/21/1952', 300),
--		( '"When did the House of Blues open the Cleveland location?" MM/YYYY', '11/2004', 300),
--		('"How many stages does the Agora have?"', '2', 300 ),
--		( '"How many performance spaces does the Playhouse Square have?"', '11', 100),
--		( '"What body part is "The Thinker" missing due to political protesting in 1970?"', 'Feet', 100),
--		( '"When was the Cleveland Botanical Garden founded?" YYYY', '1930', 100);

COMMIT;

--