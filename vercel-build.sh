#!/bin/bash

# This script tells Vercel to ignore automatic builds
# Only GitHub Actions should trigger deployments via Vercel CLI

echo "Ignoring build - deployments are handled by GitHub Actions"
exit 0