CREATE TABLE `language` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`slug`	TEXT NOT NULL UNIQUE,
	`name`	TEXT NOT NULL,
	`direction`	TEXT NOT NULL
);

CREATE TABLE `book` (
	`id`	INTEGER PRIMARY KEY AUTOINCREMENT,
	`language_id`	INTEGER NOT NULL,
	`slug`	TEXT NOT NULL,
	`name`	TEXT NOT NULL,
	`sort`	INTEGER NOT NULL,
	`location`	TEXT NOT NULL,
	FOREIGN KEY(language_id) REFERENCES language(id),
	CONSTRAINT unique_book UNIQUE (language_id, slug)
);
