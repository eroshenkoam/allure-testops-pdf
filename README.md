# allure-testops-pdf

A command line tool to generate PDF from Allure TestOps.

## Installation

Download latest version from github releases:

`wget https://github.com/eroshenkoam/allure-testops-pdf/releases/latest/download/allure-testops-pdf`

And make it executable:

`chmod +x allure-testops-pdf`

## Usage

```
allure-testops-pdf export --endpoint http://localhost:8080 \
                           --token 83f8dcd1-5ad1-47ef-9341-f60cf6bcb59b \
                           --launch-id 17 --tree-id 13
                           --output-file ./report.pdf
```

Below are a few examples of common commands. For further assistance, use the --help option on any command
