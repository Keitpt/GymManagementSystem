-- Create Packages table first
CREATE TABLE Packages (
    packageId INT PRIMARY KEY IDENTITY(1,1),  -- Use IDENTITY for SQL Server
    name VARCHAR(100),
    price DECIMAL(10, 2),
    duration INT
);

-- Create Trainers table
CREATE TABLE Trainers (
    trainerId INT PRIMARY KEY IDENTITY(1,1),  -- Use IDENTITY for SQL Server
    name VARCHAR(100),
    specialization VARCHAR(100)
);

-- Create Members table with a foreign key reference to Packages
CREATE TABLE Members (
    memberId INT PRIMARY KEY IDENTITY(1,1),  -- Use IDENTITY for SQL Server
    name VARCHAR(100),
    phone VARCHAR(15),
    email VARCHAR(100),
    packageId INT,
    FOREIGN KEY (packageId) REFERENCES Packages(packageId)
);

-- Create Schedules table with foreign key references
CREATE TABLE Schedules (
    scheduleId INT PRIMARY KEY IDENTITY(1,1),  -- Use IDENTITY for SQL Server
    memberId INT,
    trainerId INT,
    date DATE,
    timeSlot TIME,
    FOREIGN KEY (memberId) REFERENCES Members(memberId),
    FOREIGN KEY (trainerId) REFERENCES Trainers(trainerId)
);

-- Create Payments table with a foreign key reference to Members
CREATE TABLE Payments (
    paymentId INT PRIMARY KEY IDENTITY(1,1),  -- Use IDENTITY for SQL Server
    memberId INT,
    amount DECIMAL(10, 2),
    date DATE,
    FOREIGN KEY (memberId) REFERENCES Members(memberId)
);