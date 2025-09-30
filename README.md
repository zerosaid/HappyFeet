:

🐾 Veterinary Management System - Happy Feet
📖 Project Description

The Veterinary Management System - Happy Feet is a software solution designed to optimize and streamline the internal processes of the veterinary clinic.
It allows the staff to efficiently manage owners, pets, medical histories, and events, offering an organized and centralized environment for the clinic's daily operations.

This system addresses common challenges in veterinary administration, such as the lack of structured control over medical records, owner and pet information, and scheduled events.

💻 Technologies Used

Java 17 – Main programming language.

MySQL – Relational database management.

JDBC – Database connection and management.

Maven – Dependency management and project structure.

Git & GitHub – Version control and collaborative development.

⚙️ Implemented Functionalities

The system provides the following functionalities, divided by modules:

👥 Owner Module

Register new owners.

Update and delete owner records.

List all registered owners.

🐶 Pet Module

Register pets linked to an owner.

Update and delete pet records.

List all registered pets.

📋 Medical History Module

Register a pet’s medical history linked to an event.

Update and delete medical history records.

View all medical histories.

Filter histories by pet.

Display available events before registration to guide selection.

📅 Event Module

Register event types (e.g., check-up, vaccination, surgery).

List available events for selection.

🗄️ Database Model

The database is structured into the following main tables:

Owner – Stores owner information.

Pet – Linked to an owner, containing details such as name, species, and breed.

EventType – Stores predefined event types for medical histories.

MedicalHistory – Records medical information linked to a pet and an event type.

📌 It is recommended to review the Entity-Relationship Diagram (ERD) for a clear visualization of table relationships.

🚀 Installation and Execution Guide
✅ Prerequisites

JDK 17 installed.

Maven installed.

MySQL Server running.

📂 Project Setup

Clone the repository:

git clone <repository-url>
cd happyfeet


Configure the database connection in the properties file (e.g., config.properties):

db.url=jdbc:mysql://localhost:3306/happyfeet
db.user=root
db.password=your_password


Execute the database scripts in the following order:

schema.sql (creates tables).

data.sql (inserts sample data).

▶️ Running the Project

Compile and execute the application with Maven:

mvn clean install
mvn exec:java

📘 User Guide

Once executed, the system will display a console menu where you can navigate between modules:

Owners → Manage owner records.

Pets → Manage pets linked to owners.

Medical Histories → Register, view, and update records.

Events → Consult available event types.

Users simply need to select the desired option from the menu to perform the corresponding action.

👨‍💻 Author(s)

Daniel Santiago González Hernández

Joshep Martínez
