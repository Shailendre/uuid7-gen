<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UUIDv7 Generator</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
            background-color: #f9f9f9;
        }
        .container {
            text-align: center;
            background: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
            width: 600px;
        }
        .btn-generate {
            padding: 10px 20px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
            margin-bottom: 20px;
        }
        .btn-generate:hover {
            background-color: #0056b3;
        }
        .table-container {
            max-height: 200px;
            overflow-y: auto;
            border: 1px solid #ccc;
            border-radius: 5px;
            margin: 20px 0;
            background: #f9f9f9;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            text-align: left;
        }
        th, td {
            padding: 10px;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f1f1f1;
        }
        .links {
            display: flex;
            justify-content: space-around;
            margin-top: 20px;
        }
        .link-button {
            padding: 10px 20px;
            text-decoration: none;
            color: white;
            background-color: #007bff;
            border-radius: 5px;
            font-size: 14px;
            cursor: pointer;
        }
        .link-button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<div class="container">
    <button class="btn-generate">Generate UUIDv7</button>
    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>UUID7</th>
                <th>64 Bits</th>
                <th>128 Bits</th>
                <th>Timestamp</th>
            </tr>
            </thead>
            <tbody id="uuid-table-body">
            <#if uuid7??>
            <tr>
            <td>${uuid7.uuid()}</td>
            <td>${uuid7._64bits()}</td>
            <td>${uuid7._128bits()}</td>
            <td>${.now?string.xs_ms_nz}</td>
            </tr>
            </#if>
            </tbody>
        </table>
    </div>
    <div class="links">
        <a href="https://example.com/uuid7-rfc" class="link-button" target="_blank">UUID7 RFC</a>
        <a href="https://github.com/example" class="link-button" target="_blank">GitHub</a>
        <a href="https://example.com/maven" class="link-button" target="_blank">Maven Library</a>
    </div>
</div>

</body>
</html>
