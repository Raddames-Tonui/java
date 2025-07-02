
🧱 What is DAO Responsible For?
DAO (Data Access Object) is strictly for:

Purpose	DAO Should Handle?
Connecting to DB	✅ Yes
Running SQL	✅ Yes
Basic CRUD (Create, Read, Update, Delete)	✅ Yes
Returning mapped models	✅ Yes
Business Logic (e.g., scoring, ranking)	❌ No

👉 DAO is about how to access data, not what to do with it.

🧠 Where Does Logic Go?
Concern	Belongs In
"What happens when a pupil submits answers?"	Service Layer
"How do I calculate percentage score?"	Service Layer
"Top 5 pupils per exam?"	Service or ReportDAO
"Join multiple tables for a report?"	DAO (Query), then logic in Service

🧩 Updated Layer Roles
Layer	Responsibility Example
Controller	Accept HTTP, return HTTP, validate request
Service	Business logic: scoring, validation, transformations
DAO	SQL: fetch, insert, update DB only
Model	Data structure (POJO)

🔄 Example Breakdown
“Generate report of pupil answers + percentage score in an exam”

DAO:

Fetch all answers from DB (JOIN questions + choices + answers)

Service:

Count total questions

Count correct answers

Compute percentage

Return summary report model

Controller:

Accept /api/pupils/{id}/exam/{examId}/report

Return JSON report

✅ Summary
Task	DAO	Service	Controller
Save pupil registration	✅	Maybe	✅
Fetch exams by teacher	✅		✅
Calculate pupil's score	✅	✅	✅
Generate report	✅	✅	✅
Get top 5 pupils per exam	✅	✅	✅

🔥 Tip
For reusable queries like top 5 scorers, you can have a ReportDAO, which exposes read-only complex queries. But the logic (e.g., sorting, computing percentages) stays in service classes.

