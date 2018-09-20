CREATE TABLE `languages` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`slug`	TEXT NOT NULL UNIQUE,
	`name`	TEXT NOT NULL,
	`direction`	TEXT NOT NULL
);

CREATE TABLE `books` (
	`id`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,
	`language_id`	INTEGER NOT NULL,
	`slug`	TEXT NOT NULL,
	`name`	TEXT NOT NULL,
	`location`	TEXT NOT NULL
);

CREATE UNIQUE INDEX `lang_book` ON `books` (
	`language_id`,
	`slug`
);
