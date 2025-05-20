-- Создание таблиц
CREATE TABLE tenant (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE staff (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    role VARCHAR(255),
    tenant_id BIGINT NOT NULL,
    CONSTRAINT fk_staff_tenant FOREIGN KEY (tenant_id) REFERENCES tenant(id)
);

CREATE TABLE reader (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE authors (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    birth_day DATE,
    country VARCHAR(255),
    staff_id BIGINT,
    CONSTRAINT fk_authors_staff FOREIGN KEY (staff_id) REFERENCES staff(id)
);

CREATE TABLE categories (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    parent_id BIGINT,
    staff_id BIGINT,
    CONSTRAINT fk_categories_parent FOREIGN KEY (parent_id) REFERENCES categories(id),
    CONSTRAINT fk_categories_staff FOREIGN KEY (staff_id) REFERENCES staff(id)
);

CREATE TABLE books (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255),
    author_id BIGINT,
    category_id BIGINT,
    publish_year INT,
    available BOOLEAN,
    CONSTRAINT fk_books_author FOREIGN KEY (author_id) REFERENCES authors(id),
    CONSTRAINT fk_books_category FOREIGN KEY (category_id) REFERENCES categories(id)
);

CREATE TABLE book_loans (
    id BIGSERIAL PRIMARY KEY,
    reader_id BIGINT,
    book_id BIGINT,
    loan_date DATE,
    due_date DATE,
    return_date DATE,
    CONSTRAINT fk_loans_reader FOREIGN KEY (reader_id) REFERENCES reader(id),
    CONSTRAINT fk_loans_book FOREIGN KEY (book_id) REFERENCES books(id)
);

-- Заполнение тестовыми данными
INSERT INTO tenant (id, name) VALUES (1, 'TestTenant');

INSERT INTO staff (id, tenant_id, email, name, password, role)
VALUES (1, 1, 'admin@example.com', 'Admin User', '$2a$10$7K0UO8s8Y1nh/...', 'ADMIN'); -- подставь свой bcrypt-хеш

INSERT INTO reader (id, name, password)
VALUES (1, 'Reader One', 'readerpassword');

INSERT INTO authors (id, staff_id, name, birth_day, country)
VALUES (1, 1, 'Author One', '1980-05-12', 'USA');

INSERT INTO categories (id, name, parent_id, staff_id)
VALUES
  (1, 'Fiction', NULL, 1),
  (2, 'Literature', NULL, 1),
  (3, 'Science Fiction', 2, 1),
  (4, 'Fantasy', 2, 1);

INSERT INTO books (id, title, author_id, category_id, publish_year, available)
VALUES (1, 'Book Title One', 1, 1, 2001, TRUE);

INSERT INTO book_loans (id, reader_id, book_id, loan_date, due_date, return_date)
VALUES (1, 1, 1, '2025-04-20', '2025-05-20', '2025-05-15');
