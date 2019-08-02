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
    html {
        background-image: url(https://store-images.s-microsoft.com/image/apps.60733.9007199266244048.3346be15-f4a7-4b2f-956a-5ab635e87d5f.24cc3ea3-c887-4873-812a-23604ff34375);
        background-size: cover;
        background-position-y: -80%;
    }

    .container {
        padding-right: 10%;
        padding-left: 10%;
        position: absolute;
        top: 10%;
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

            <h4>Select files from your computer and click Upload</h4>
            <form id="js-upload-submit" action="/" method="post" enctype="multipart/form-data">
                <div class="form-inline">
                    <div class="form-group">
                        <input id="js-upload-files-input" type="file" name="files" multiple>
                    </div>
                    <button type="submit" class="btn btn-sm btn-primary" id="js-upload-files">Upload files</button>
                </div>
            </form>

            <c:if test="${not empty files}">
                <div class="processed-files">
                    <h3>Uploaded files</h3>
                    <div class="list-group">
                        <c:forEach var="file" items="${files}">
                            <a href="#" class="list-group-item list-group-item-success" data-file-id="${file.id}">
                                <span class="badge alert-success pull-right">Download</span>
                                <span><c:out value="${file.fileName}"/></span>
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
        $('#js-upload-submit').on('submit', function(e) {
            if (!$('#js-upload-files-input').val()) {
                e.preventDefault();
                alert('Please select files to upload.');
                return false;
            }

            $('#js-upload-files').attr('disabled', 'disabled').text('Please wait');
            return true;
        });

        $('.list-group-item').on('click', function() {
            window.location = '<%=request.getContextPath()%>/download?fileId=' + $(this).attr('data-file-id');
        });
    });
</script>
</body>

</html>