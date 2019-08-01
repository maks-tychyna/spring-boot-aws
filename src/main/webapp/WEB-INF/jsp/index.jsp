<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>AWS File Uploader</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>

<style type="text/css">
    .container {
        padding-right: 10%;
        padding-left: 10%;
        position: absolute;
        top: 50%;
        transform: translateY(-50%);
        width: 100%;
    }

    .processed-files {
        margin-top: 100px;
    }
</style>

<div class="container">
    <div class="panel panel-default">
        <div class="panel-heading"><strong>Upload Files</strong> <small>to AWS S3</small></div>
        <div class="panel-body">

            <!-- Standar Form -->
            <h4>Select files from your computer</h4>
            <form id="js-upload-submit" action="/" method="post" enctype="multipart/form-data">
                <div class="form-inline">
                    <div class="form-group">
                        <input type="file" name="files" multiple>
                    </div>
                    <button type="submit" class="btn btn-sm btn-primary" id="js-upload-files">Upload files</button>
                </div>
            </form>

            <c:if test="${not empty files}">
                <div class="processed-files">
                    <h3>Processed files</h3>
                    <div class="list-group">
                        <c:forEach var="file" items="${files}">
                            <a href="#" class="list-group-item list-group-item-success">
                                <span class="badge alert-success pull-right"><c:out value="${file.fileName}"/></span>
                            </a>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>

<script src="/js/jquery.min.js"></script>
<script src="/js/bootstrap.min.js"></script>

<script type="text/javascript">
    $(document).ready(function() {
        $('#js-upload-submit').on('submit', function() {
            $('#js-upload-files').attr('disabled', 'disabled').val('Please wait');
            return true;
        });
    });
</script>
</body>

</html>