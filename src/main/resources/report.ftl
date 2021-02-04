<#-- @ftlvariable name="meta" type="io.github.eroshenkoam.allure.dto.ReportMeta" -->
<#-- @ftlvariable name="tree" type="io.github.eroshenkoam.allure.dto.ResultTree" -->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous"/>
    <style>
        .tree-group {
        }

        .tree-group__name {
        }

        .tree-group__status {
            float: right;
        }

        .tree-leaf {
        }

        .tree-leaf__name {
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
            <h1>Overview</h1>
            <dl>
                <dt>Name</dt>
                <dd>${meta.name}</dd>
                <dt>Created Date</dt>
                <dd>${meta.createdDate?datetime}</dd>
            </dl>
        </div>
    </div>
</div>

<div class="container-fluid">
    <div class="row">
        <div class="col">
            <h1>Summary</h1>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th scope="col" style="width: 80%">Name</th>
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
                        <td>
                            <span class="label label-passed">${group.passed}</span>
                        </td>
                        <td>
                            <span class="label label-failed">${group.failed}</span>
                        </td>
                        <td>
                            <span class="label label-broken">${group.broken}</span>
                        </td>
                        <td>
                            <span class="label label-skipped">${group.skipped}</span>
                        </td>
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
            <table class="table">
                <thead>
                <th style="width: 80%;">Name</th>
                <th style="width: 20%">Status</th>
                </thead>
                <tbody>
                <@treeGroups tree.groups 0/>
                <@treeLeaves tree.leaves 0/>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

<#macro treeGroups groups level>
    <#list groups as group>
        <tr class="tree-group">
            <td>
                <div class="tree-group__name" style="padding-left: ${level*20}px">
                    ${group.name?html}
                </div>
            </td>
            <td>
                <div class="tree-group__status">
                    <span class="label label-passed">${group.passed}</span>
                    <span class="label label-failed">${group.failed}</span>
                    <span class="label label-broken">${group.broken}</span>
                    <span class="label label-skipped">${group.skipped}</span>
                </div>
            </td>
        </tr>
        <@treeGroups group.groups level+1/>
        <@treeLeaves group.leaves level+1/>
    </#list>
</#macro>

<#macro treeLeaves leaves level>
    <#list leaves as leaf>
        <tr class="tree-leaf">
            <td>
                <div class="tree-leaf__name" style="padding-left: ${level*20}px">
                    ${leaf.name?html}
                </div>
            </td>
            <td>
                <div class="tree-leaf__status">
                    <span class="label label-${leaf.status}">${leaf.status}</span>
                </div>
            </td>
        </tr>
    </#list>
</#macro>
