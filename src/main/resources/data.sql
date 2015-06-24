INSERT INTO questionnaires (created_at, expired_at, title, id)
VALUES (now(), now(), 'JJUGナイトセミナー6月アンケート', 'c9dba956458d430c9acdab4b0bbfd1b0');
INSERT INTO questionnaire (id, title) VALUES (4, 'JJUGナイトセミナーの参加回数は？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (13, '初めて', 1, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (14, '2回目', 2, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (15, '3回目', 4, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (16, '4回目以上', 4, 0);
INSERT INTO questionnaire (id, title) VALUES (5, 'Reactive Streamsを知っていた？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (17, 'はい', 2, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (18, 'いいえ', 2, 0);
INSERT INTO questionnaire (id, title) VALUES (6, '普段最もよく使用している言語は？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (19, 'Java', 4, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (20, 'Scala', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (21, 'Groovy', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (22, 'Clojure', 0, 0);
;
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (23, 'Kotlin', 0, 0);
INSERT INTO questionnaire (id, title) VALUES (11, 'RxJavaを？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (37, '聞いたことがない', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (38, '知っている', 2, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (39, '触った事がある', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (40, '使っている', 0, 0);
INSERT INTO questionnaire (id, title) VALUES (12, 'Akka Streamsを？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (41, '聞いたことがない', 1, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (42, '知っている', 1, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (43, '触った事がある', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (44, '使っている', 0, 0);
INSERT INTO questionnaire (id, title) VALUES (13, 'Reactorを？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (45, '聞いたことがない', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (46, '知っている', 1, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (47, '触った事がある', 1, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (48, '使っている', 0, 0);
INSERT INTO questionnaire (id, title) VALUES (14, 'Ratpackを？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (49, '聞いたことがない', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (50, '知っている', 2, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (51, '触った事がある', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (52, '使っている', 0, 0);
INSERT INTO questionnaire (id, title) VALUES (15, 'Vert.x 3を？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (53, '聞いたことがない', 2, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (54, '知っている', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (55, '触った事がある', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (56, '使っている', 0, 0);
INSERT INTO questionnaire (id, title) VALUES (16, 'Slick 3を？');
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (57, '聞いたことがない', 2, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (58, '知っている', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (59, '触った事がある', 0, 0);
INSERT INTO questionnaire_item (item_id, label, VALUE, version) VALUES (60, '使っている', 0, 0);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 4);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 5);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 6);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 11);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 12);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 13);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 14);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 15);
INSERT INTO questionnaires_values (questionnaires_id, values_id) VALUES ('c9dba956458d430c9acdab4b0bbfd1b0', 16);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (4, 13);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (4, 14);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (4, 15);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (4, 16);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (5, 17);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (5, 18);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (6, 19);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (6, 20);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (6, 21);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (6, 22);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (6, 23);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (11, 37);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (11, 38);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (11, 39);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (11, 40);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (12, 41);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (12, 42);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (12, 43);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (12, 44);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (13, 45);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (13, 46);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (13, 47);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (13, 48);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (14, 49);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (14, 50);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (14, 51);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (14, 52);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (15, 53);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (15, 54);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (15, 55);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (15, 56);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (16, 57);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (16, 58);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (16, 59);
INSERT INTO questionnaire_items (questionnaire_id, items_itemId) VALUES (16, 60);