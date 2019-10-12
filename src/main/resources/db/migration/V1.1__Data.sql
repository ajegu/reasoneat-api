-- Seasons
INSERT INTO season(season_id, name) VALUES
('4b66ce81-8472-4595-957f-22bf88f8e9f3', 'Printemps')
, ('cc16cb2e-9f86-43ce-bd93-b374a457ee7e', 'Été')
, ('4c5e81c7-123e-46c5-b0c8-dd2325594204', 'Automne')
, ('0309156c-64ba-47b2-aa90-50dd382b0da3', 'Hivers')
;

INSERT INTO month(month_id, name, season_id) VALUES
('36ad4778-48ba-43d6-ac4f-7bca0033f76d', 'Janvier', '0309156c-64ba-47b2-aa90-50dd382b0da3')
,('1246503b-435c-4d94-8968-a45fcbe92832', 'Février', '0309156c-64ba-47b2-aa90-50dd382b0da3')
,('da23c8c0-4089-44c3-9528-8e79f538336f', 'Mars', '0309156c-64ba-47b2-aa90-50dd382b0da3')
,('1aed6510-63ee-4f99-bc80-a62213c94e97', 'Avril', '4b66ce81-8472-4595-957f-22bf88f8e9f3')
,('e040f5b5-672c-4e65-826a-fb83af0e5705', 'Mai', '4b66ce81-8472-4595-957f-22bf88f8e9f3')
,('d154db2f-66ba-4255-a1d1-378229355a86', 'Juin', '4b66ce81-8472-4595-957f-22bf88f8e9f3')
,('7c717985-fd05-4faf-9f1a-ceac77b38f3f', 'Juillet', 'cc16cb2e-9f86-43ce-bd93-b374a457ee7e')
,('dd69d178-8a74-4949-9382-e4b15b7956fd', 'Août', 'cc16cb2e-9f86-43ce-bd93-b374a457ee7e')
,('5edf0544-cb18-4945-9bd8-45f818307e68', 'Septembre', 'cc16cb2e-9f86-43ce-bd93-b374a457ee7e')
,('fc732f5a-0813-4cfa-969e-00b5be7bea1d', 'Octobre', '0309156c-64ba-47b2-aa90-50dd382b0da3')
,('dd3e975b-ff02-4295-9778-9a43bf832806', 'Novembre', '0309156c-64ba-47b2-aa90-50dd382b0da3')
,('0639483e-5ec3-44c5-af49-d1d2878b6dbc', 'Décembre', '0309156c-64ba-47b2-aa90-50dd382b0da3')
;

INSERT INTO category(category_id, name, created_at) VALUES
('5072a1a0-7d24-44bc-9f8f-80d477529ac8', 'Légumes', current_timestamp)
,('3c8b542a-03c3-4ff5-9f95-2b642dec96a2', 'Fruits', current_timestamp)
,('a10ccd49-9dda-494f-9be7-9fc241f76381', 'Céréales', current_timestamp)
,('9925de99-b46f-470d-9431-1dbe40fb738d', 'Légumineuses', current_timestamp)
;