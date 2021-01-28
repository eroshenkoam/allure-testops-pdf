<#-- @ftlvariable name="tree" type="io.github.eroshenkoam.allure.dto.ResultTree" -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous"/>
    <style>
        .tree-group {
            display: block;
            padding-bottom: 5px;
        }

        .tree-group__name {
            display: inline;
        }

        .tree-group__status {
            float: right;
        }

        .tree-leaf {
            display: block;
            padding-bottom: 5px;
        }

        .tree-leaf__name {
            display: inline;
        }

        .tree-leaf__status {
            float: right;
        }

        .label {
            display: inline;
            white-space: nowrap;
            word-break: keep-all;
            vertical-align: baseline;
            letter-spacing: 1px;
            color: #fff;
            padding: 2px 4px;
            font-size: 80%;
            border-radius: 3px;
            border: 1px solid #4c4c4c;
            cursor: default;
            text-decoration: none;
        }

        .label-passed {
            background: #97cc64;
            color: #000;
            fill: #000;
        }

        .label-failed {
            background: #fd5a3e;
            color: #000;
            fill: #000;
        }

        .label-broken {
            background: #ffd050;
            color: #000;
            fill: #000;
        }

        .label-skipped {
            background: #aaa;
            color: #000;
            fill: #000;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col">
            <h1>Summary</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col" style="width: 50%">Name</th>
                    <th scope="col">Passed</th>
                    <th scope="col">Failed</th>
                    <th scope="col">Broken</th>
                    <th scope="col">Skipped</th>
                </tr>
                </thead>
                <tbody>
                <#list tree.groups as group>
                    <tr>
                        <td>${group.name}</td>
                        <td>${group.passed}</td>
                        <td>${group.failed}</td>
                        <td>${group.broken}</td>
                        <td>${group.skipped}</td>
                    </tr>
                </#list>
                </tbody>
            </table>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm">
            <h1>Details</h1>
            <@treeGroups tree.groups/>
            <@treeLeaves tree.leaves/>
        </div>
    </div>
</div>
</body>
</html>

<#macro treeGroups groups>
    <#list groups as group>
        <ul>
            <li>
                <div class="tree-group">
                    <div class="tree-group__name">
                        <span class="tree-group__name">${group.name?html}</span>
                    </div>
                    <div class="tree-group__status">
                        <span class="label label-passed">${group.passed}</span>
                        <span class="label label-failed">${group.failed}</span>
                        <span class="label label-broken">${group.broken}</span>
                        <span class="label label-skipped">${group.skipped}</span>
                    </div>
                </div>
                <div class="tree-child">
                    <@treeGroups group.groups/>
                    <@treeLeaves group.leaves/>
                </div>
            </li>
        </ul>
    </#list>
</#macro>

<#macro treeLeaves leaves>
    <#list leaves as leaf>
        <ul>
            <li>
                <div class="tree-leaf">
                    <div class="tree-leaf__name">${leaf.name?html}</div>
                    <div class="tree-leaf__status">
                        <span class="label label-${leaf.status}">${leaf.status}</span>
                    </div>
                </div>
            </li>
        </ul>
    </#list>
</#macro>
