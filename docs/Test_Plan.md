# PFM Project Test Plan

**Version:** 1.0  
**Prepared by:** Testing Team

---

# Purpose

This document outlines the testing plan for the Personal Finance Manager (PFM) project. The goal of testing is to verify that each module works according to its requirements, handles invalid inputs correctly, and integrates properly with other modules.

---

# Accounts Module Test Plan

1. Verify a user can create an account with a valid username, password, secret question, and secret answer.

2. Verify usernames can be updated successfully.

3. Verify passwords can be updated when valid information is provided.

4. Verify passwords are hashed and are not stored in plain text.

5. Verify users can log in with correct credentials.

6. Verify users cannot log in with incorrect usernames or passwords.

7. Verify logout successfully ends the user session.

8. Verify accounts can be deleted successfully.

9. Verify invalid usernames are rejected.

10. Verify invalid passwords are rejected.

11. Verify account information can be correctly written to and read from files.

---

# Storage Module Test Plan

1. Verify a budget can be created for a user.

2. Verify existing budgets can be loaded correctly.

3. Verify budget information can be updated.

4. Verify budgets can be deleted.

5. Verify the system correctly identifies whether a budget exists for a specific year.

6. Verify transactions can be added to a budget.

7. Verify transactions can be retrieved by month.

8. Verify transactions can be retrieved by category.

9. Verify CSV files can be imported successfully.

10. Verify invalid CSV records are detected and filtered.

11. Verify CSV reports can be exported correctly.

12. Verify account files can be saved, loaded, and deleted.

13. Verify file paths are resolved correctly.

14. Verify the system creates required data directories when missing.

---

# Validation Module Test Plan

1. Verify usernames follow all required formatting rules.

2. Verify passwords meet security requirements.

3. Verify invalid passwords are rejected.

4. Verify secret questions and answers cannot be blank.

5. Verify filenames follow the required format.

6. Verify only valid CSV files are accepted.

7. Verify CSV headers match expected formatting.

8. Verify the system detects whether a file exists for a given year.

9. Verify dates follow the correct format.

10. Verify transactions match the correct year.

11. Verify only valid spending categories are accepted.

12. Verify transaction amounts are within valid ranges.

13. Verify complete records are accepted and invalid records are rejected.

---

# Data Audit Module Test Plan

1. Verify yearly audits can be performed successfully.

2. Verify duplicate transactions are detected.

3. Verify unique transactions are not marked as duplicates.

4. Verify unusual transactions are identified as anomalies.

5. Verify average transaction amounts are calculated correctly.

6. Verify excluded categories are ignored during audits.

7. Verify categories can be added and removed from the exclusion list.

8. Verify audit results correctly display duplicate transactions.

9. Verify audit results correctly display anomalies.

10. Verify audit summaries display accurate information.

---

# Insights Module Test Plan

1. Verify spending insights can be generated from budget data.

2. Verify yearly financial analysis works correctly.

3. Verify insights are displayed to the user.

4. Verify insights can be exported.

5. Verify monthly spending totals are calculated correctly.

6. Verify category totals are calculated correctly.

7. Verify category percentages are accurate.

8. Verify average monthly spending calculations are correct.

9. Verify surplus recommendations are generated when income exceeds expenses.

10. Verify deficit recommendations are generated when expenses exceed income.

11. Verify spending patterns can be compared.

12. Verify total income, expenses, and net balance calculations are correct.

13. Verify budget status is correctly classified as SURPLUS, DEFICIT, or BALANCED.

---

# Reports Module Test Plan

1. Verify annual reports generate correctly.

2. Verify monthly summaries generate correctly.

3. Verify category spending reports generate correctly.

4. Verify budget summaries generate correctly.

5. Verify users can select different report types.

6. Verify reports display correctly in the console.

7. Verify reports can be exported to CSV files.

8. Verify currency formatting displays correctly.

9. Verify report headers display correctly.

10. Verify report menu options work correctly.

11. Verify users can return to the main menu.

---

# Integration Module Test Plan

1. Verify all modules successfully register with the application.

2. Verify the application initializes all modules correctly.

3. Verify the main menu displays all available modules.

4. Verify user menu selections route to the correct module.

5. Verify invalid menu selections are handled correctly.

6. Verify the application continues running until exit is selected.

7. Verify shutdown completes without errors.

8. Verify communication between Accounts, Storage, Reports, Insights, and Data Audit modules works correctly.

---

# Final System Testing

1. Verify a new user can create an account and begin using PFM.

2. Verify a user can create budgets and transactions.

3. Verify stored financial information remains available after restarting.

4. Verify reports and insights use accurate stored data.

5. Verify invalid inputs do not crash the application.

6. Verify all modules work together as one complete system.
