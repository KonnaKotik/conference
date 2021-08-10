INSERT INTO public.conference_user (email, first_name, last_name, password, user_role, user_state) VALUES ('string', 'string', 'string', '$2a$10$L./WEA7a83/jGLO/9P0OeOGIcVVslAV/eTtvTCd9gnblwVhqCmLm6', 'LISTENER', 'ACTIVE' );
INSERT INTO public.conference_user (email, first_name, last_name, password, user_role, user_state) VALUES ('admin', 'admin', 'admin', '$2a$10$ObM0InZF2NwwqOF55jd.V.us0Ad8T3zm6k0EXnkhciW2EWppGTIdu', 'ADMIN', 'ACTIVE' );
INSERT INTO public.conference_user (email, first_name, last_name, password, user_role, user_state) VALUES ('speaker', 'speaker', 'speaker', '$2a$10$IYMLlWQ1HADZBSK94vCeFOKnS060tSm9eOC5c9W70gVlfsVeB4PRq', 'SPEAKER', 'ACTIVE' );

INSERT INTO public.room (name) VALUES ('AI');
INSERT INTO public.room (name) VALUES ('PRODUCT_DESIGN');
INSERT INTO public.room (name) VALUES ('SOFTWARE');
INSERT INTO public.room (name) VALUES ('IT');
INSERT INTO public.room (name) VALUES ('ROBOTIC');
INSERT INTO public.room (name) VALUES ('DEVELOPMENT');

INSERT INTO public.talk (description, link, name) VALUES ('Today, artificial intelligence helps doctors diagnose patients, pilots fly commercial aircraft, and city planners predict traffic.', 'https://www.youtube.com/watch?v=0yCJMt9Mx9c&ab_channel=TED-Ed', 'How does artificial intelligence learn?');
INSERT INTO public.talk (description, link, name) VALUES ('Surgeon and inventor Catherine Mohr tours the history of surgery , then demos some of the newest tools for surgery through tiny incisions, performed using nimble robot hands.', 'https://www.youtube.com/watch?v=Q4ZFud69hfc&ab_channel=TexoBot', 'Surgery''s past, present and robotic future');
INSERT INTO public.talk (description, link, name) VALUES ('Can an artificial machine really think? For many, these have been vital considerations for the future of artificial intelligence.', 'https://www.youtube.com/watch?v=3wLqsRLvV-c&ab_channel=TED-Ed', 'he Turing test: Can a computer pass for a human?');


INSERT INTO public.schedule (date_end, date_start, room_id, fk_talk_id) VALUES ('2021-08-11 14:12:39.000000', '2021-08-10 12:12:58.000000', 2, 1);
INSERT INTO public.schedule (date_end, date_start, room_id, fk_talk_id) VALUES ('2021-08-12 14:00:00.000000', '2021-08-12 12:00:00.000000', 2, 2);
INSERT INTO public.schedule (date_end, date_start, room_id, fk_talk_id) VALUES ('2021-08-11 12:00:00.000000', '2021-08-11 14:00:00.000000', 1, 3);

INSERT INTO public.user_talk (user_id, talks_id) VALUES (3, 1);
INSERT INTO public.user_talk (user_id, talks_id) VALUES (3, 2);
INSERT INTO public.user_talk (user_id, talks_id) VALUES (3, 3);