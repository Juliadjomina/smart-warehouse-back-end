
CREATE TABLE storage (
    id BIGSERIAL NOT NULL,
    name  VARCHAR(50) NOT NULL,
    capacity INTEGER NOT NULL,
    CONSTRAINT pk_storage PRIMARY KEY (id)
);

CREATE TABLE product (
                      id BIGSERIAL NOT NULL,
                      name VARCHAR(50) NOT NULL,
                      serialnumber VARCHAR(50),
                      dateofpurchase  TIMESTAMP,
                      length INTEGER,
                      width INTEGER,
                      depth INTEGER,
                      material VARCHAR(50),
                      storage_id INTEGER NOT NULL,
                      CONSTRAINT pk_product PRIMARY KEY (id),
                      CONSTRAINT fk_product_storage_id FOREIGN KEY (storage_id) REFERENCES storage (id)
);
