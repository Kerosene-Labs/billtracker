#!/bin/bash
set -e

BASE_BRANCH="develop"
FEATURE_BRANCH="BIL-6-update-fixed"
PR_TITLE="BIL-6: Building Workflow"
PR_BODY="Building workflow"

PR_URL=$(gh pr create --base "$BASE_BRANCH" --head "$FEATURE_BRANCH" --title "$PR_TITLE" --body "$PR_BODY" | grep "https")

echo "Pull request created: $PR_URL"

# Merge the pull request
gh pr merge "$PR_URL" --merge --admin
echo "Pull request merged and branch deleted."