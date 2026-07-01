# PFM Project Issue Workflow

**Version:** 1.0  
**Prepared by:** Testing Team

---

## Purpose

This document describes the workflow for reporting, assigning, fixing, testing, and closing issues throughout the PFM project. Following this workflow will help ensure that issues are tracked consistently and that communication between teams remains organized.

---

## 1. Creating an Issue

Any project member may create an issue whenever they discover a bug, defect, or task that requires attention.

When creating an issue, include:

- A clear, descriptive title
- A detailed explanation of the problem
- Steps to reproduce the issue (if applicable)
- Expected behavior
- Actual behavior
- Screenshots or supporting files (if applicable)
- The appropriate Team label
- Status set to **New**

The issue should initially be assigned to the Team Leader responsible for the team that will resolve the issue.

---

## 2. Team Leader Review

After receiving the issue, the assigned Team Leader will:

- Review the issue for completeness.
- Request additional information if necessary.
- Determine which team member should resolve the issue.
- Assign the issue to the appropriate developer.

Once the developer begins working on the issue, they should update the issue status to **Open**.

---

## 3. Issue Resolution

The assigned developer is responsible for:

- Investigating the reported issue.
- Implementing a solution.
- Testing the fix locally before submitting code.
- Committing and pushing the changes to GitHub.

After submitting the fix, the developer should:

- Reassign the issue to the Test Team Leader.
- Update the issue status to **Fixed**.
- Include a comment briefly explaining the changes made and reference the commit or pull request if applicable.

---

## 4. Testing

The Test Team Leader will assign the issue to an available tester.

The tester will:

- Verify that the reported issue has been resolved.
- Perform any necessary regression testing to ensure the fix did not introduce new problems.
- Document the testing results in the issue comments.

If the issue is successfully resolved:

- Change the status to **Closed**.

If the issue is not resolved or introduces additional problems:

- Change the status back to **Open**.
- Reassign the issue to the original developer.
- Explain the remaining problem in the comments.

---

## 5. Communication

All project members should use issue comments to communicate throughout the issue's lifecycle.

Comments should be used to:

- Ask questions
- Provide updates
- Explain implementation decisions
- Document testing results
- Request clarification when needed

Keeping all communication within the issue helps maintain a complete history of the work performed.

---

## Issue Status Definitions

| Status | Meaning |
|---------|---------|
| **New** | Issue has been reported and is awaiting review by the appropriate Team Leader. |
| **Open** | A developer has begun working on the issue. |
| **Fixed** | The developer believes the issue has been resolved and it is ready for testing. |
| **Closed** | The issue has been successfully verified by the Testing Team and requires no further action. |

---

## Workflow Summary

```
Issue Created
      │
      ▼
Status: New
      │
      ▼
Assigned to Team Leader
      │
      ▼
Assigned to Developer
      │
      ▼
Status: Open
      │
      ▼
Developer Fixes Issue
      │
      ▼
Status: Fixed
      │
      ▼
Assigned to Test Team
      │
      ▼
Tester Verifies Fix
      │
      ├──────────────► Pass
      │                  │
      │                  ▼
      │            Status: Closed
      │
      └──────────────► Fail
                         │
                         ▼
                   Status: Open
                         │
                         ▼
                 Returned to Developer
```
