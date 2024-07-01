-- Crear tabla de usuarios si no existe
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    referral_code VARCHAR(6) UNIQUE,
    level INT
);

-- Crear tabla de códigos de referido si no existe
CREATE TABLE IF NOT EXISTS referral_codes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    code VARCHAR(6) UNIQUE NOT NULL,
    used BOOLEAN DEFAULT FALSE
);

-- Insertar los primeros 10 códigos de referido
INSERT INTO referral_codes (code) VALUES
('ABC123'), ('DEF456'), ('GHI789'), ('JKL012'), ('MNO345'),
('PQR678'), ('STU901'), ('VWX234'), ('YZA567'), ('BCD890');

-- Insertar usuarios de prueba
-- La contraseña 'password' está encriptada usando BCrypt
INSERT INTO usuarios (username, password, email, referral_code, level) VALUES
('testuser1', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser1@example.com', 'ABC123', 0),
('testuser2', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser2@example.com', 'DEF456', 0),
('testuser3', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser3@example.com', 'GHI789', 0),
('testuser4', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser4@example.com', 'JKL012', 0),
('testuser5', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser5@example.com', 'MNO345', 0),
('testuser6', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser6@example.com', 'PQR678', 0),
('testuser7', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser7@example.com', 'STU901', 0),
('testuser8', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser8@example.com', 'VWX234', 0),
('testuser9', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser9@example.com', 'YZA567', 0),
('testuser10', '$2a$10$7Qw8F6asEUpV2CvULVYRSOrPHFwPIo7m7P8/jSKMRfAmgcsZQVJ.u', 'testuser10@example.com', 'BCD890', 0);

