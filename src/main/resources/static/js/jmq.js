// 表单非空校验
function check(e) {
    let value = e.value;
    if (value == null || value === "") {
        let label = e.label;
        alert("此项不能为空！请输入内容");
    }
}

function questionComment() {

    let parentId = $(" #parentId ").val();// 帖子id
    let parentUserId = $(" #parentUserId ").val();// 帖子发布人id
    let parentType = 1; // 1 代表帖子
    let commentContent = $(" #comment-content ").val();
    let type = 1;

    if (!commentContent) {
        alert("请输入评论内容！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "type": type,
            "content": commentContent,
            "parentType": parentType,
            "parentUserId": parentUserId,
            "articleId": parentId
        }),
        success: function (response) {
            console.log(response);
            if (response.code == 200) {
                window.location.reload();
                // alert(response.message);

                // debugger;
                $(" #comment-content ").val("");
                // $(" #sub-comment-content ").val("");
                // appendToTarget(parentId, type)
            } else {
                alert("提交失败")
            }
        },
        dataType: "json"
    });
}

/**
 * 二级评论上传
 * @param e
 */
function subComment(e) {

    let id = e.getAttribute("data-id");
    let photo = e.getAttribute("data-photo");
    let username = e.getAttribute("data-username");
    let parentId = id;
    let parentUserId = e.getAttribute("data-parent-userId");
    let articleId = e.getAttribute("data-article-id");
    console.log(parentUserId)
    let parentType = 1; // 1 代表评论贴
    let commentContent = $(" #sub-comment-" + id).val();
    commentContent = html2Escape(commentContent); // HTML 转普通文本防止代码注入
    let type = 2; // 2 代表二级评论
    let parentUsername = e.getAttribute("data-parent-username");
    let parentContent = e.getAttribute("data-parent-comment-content");

    let subCommentInputWindow = $("#sub-comment-window" + id);


    if (!commentContent) {
        alert("请输入评论内容！");
        return;
    }
    $.ajax({
        type: "POST",
        url: "/comment",
        contentType: "application/json",
        data: JSON.stringify({
            "parentId": parentId,
            "type": type,
            "content": commentContent,
            "parentType": parentType,
            "parentUsername": parentUsername,
            "parentContent": parentContent,
            "parentUserId": parentUserId,
            "articleId": articleId
        }),
        success: function (response) {
            console.log(response);
            if (response.code === 200) {
                // window.location.reload();
                // 追加评论到页面
                subCommentInputWindow.before(" <div class=\"media\">\n" +
                    "                                            <img src=\"" + photo + "\" class=\"mr-3 rounded\" width=\"45px\"\n" +
                    "                                                 height=\"45px\">\n" +
                    "                                            <div class=\"media-body\">\n" +
                    "                                                <p class=\"mt-0 comment-info\">\n" +
                    "                                                    <span style=\"font-weight: bold;color: gray\"\n" +
                    "                                                          >" + username + "</span>&nbsp;&nbsp;\n" +
                    "                                                    <span style=\"font-size: 12px;color: #999;line-height: 200%;\"\n" +
                    "                                                          >" + moment(response.createTime).format('YYYY-MM-DD hh:mm') + "</span>\n" +
                    "                                                </p>\n" +
                    "                                                <p class=\"comment-content\"\n" +
                    "                                                   >" + response.data.content + "</p>\n" +
                    "                                                <a class=\"sub-comment-button\" href='#sub-comment-window" + id + "'" + " style=\"font-size: 12px;color: #999;line-height: 200%;\"\n" +
                    "                                                      onclick=\"reply(this)\" data-id=\"" + parentId + "\"\n" +
                    "                                                      data-user=\"" + username + "\">回复</a>\n" +
                    "                                            </div>\n" +
                    "                                        </div>", subCommentInputWindow);
                $(" #sub-comment-" + id).val("");

            } else {
                alert("提交失败")
            }
        },
        dataType: "json"
    });
}

// 删除讨论帖
function deleteQuestion(e) {
    let id = e.getAttribute("data-id");
    let curr = document.getElementById("profile-question-" + id);

    $.ajax({
        type: "POST",
        url: "/question/delete",
        contentType: "application/json",
        data: JSON.stringify({
            "id": id
        }),
        success: function (response) {
            console.log(response);
            if (response.code == 200) {
                curr.parentNode.removeChild(curr);
                alert("删除成功")
            } else {
                alert("删除失败")
            }
        },
        dataType: "json"
    });

}


/**
 * 展开二级评论
 */
function collapseComments(e) {
    let id = e.getAttribute("data-id");
    let target = e.getAttribute("data-target")
    let comments = $(target);
    // debugger;
    // 获取一下二级评论的展开状态
    var collapse = e.getAttribute("data-collapse");
    if (collapse) {
        // 折叠二级评论
        comments.removeClass("collapse show");
        comments.addClass("collapse")
        e.removeAttribute("data-collapse");
        e.classList.remove("active");
        e.classList.add("collapsed")
    } else {
        var subCommentContainer = $(target);
        if (subCommentContainer.children().length !== 1) {
            //展开二级评论
            comments.addClass("collapse show");
            // 标记二级评论展开状态
            if (e.getAttribute("class")) {
                e.classList.remove("collapsed");
            }
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");

        } else {
            $.getJSON("/subComment?id=" + id, function (data) {
                var len=data.data.length;
                $.each(data.data.reverse(), function (index, comment) {

                    comments.prepend(" <div class=\"media\">\n" +
                        "                                            <img src=\"" + comment.photo + "\" class=\"mr-3 rounded\" width=\"45px\"\n" +
                        "                                                 height=\"45px\">\n" +
                        "                                            <div class=\"media-body\">\n" +
                        "                                                <p class=\"mt-0 comment-info\">\n" +
                        "                                                    <span style=\"font-weight: bold;color: gray\"\n" +
                        "                                                          >" + comment.username + "</span>&nbsp;&nbsp;\n" +
                        "                                                    <span style=\"font-size: 12px;color: #999;line-height: 200%;\"\n" +
                        "                                                          >" + moment(comment.createTime).format('YYYY-MM-DD hh:mm') + "</span>\n" +"<span style=\"font-weight: bold;font-size: 12px;color: gray\">&nbsp;&nbsp;"+len+"#</span>"+
                        "                                                </p>\n" +
                        "                                                <p class=\"comment-content\"\n" +
                        "                                                   >" + comment.content + "</p>\n" +
                        "                                                <span class=\"sub-comment-button\" href='#sub-comment-window" + id + "'" + " style=\"font-size: 12px;color: #999;line-height: 200%;\"\n" +
                        "                                                      onclick=\"reply(this)\" data-id=\"" + comment.parentId + "\"\n" +
                        "                                                      data-user=\"" + comment.username + "\">回复</span>\n" +
                        "                                            </div>\n" +
                        "                                        </div>");
                    len=len-1;
                });
            });
            //展开二级评论
            comments.addClass("collapse show");
            // 标记二级评论展开状态
            if (e.getAttribute("class")) {
                e.classList.remove("collapsed");
            }
            e.setAttribute("data-collapse", "in");
            e.classList.add("active");

        }
        // $.getJSON("/comment/" + id, function (data) {
        //     console.log(data)
        //     $.each(data.data.reverse(), function (index, comment) {
        //         var mediaLeftElement = $("<div/>", {
        //             "class": "media-left"
        //         }).append($("<img/>", {
        //             "class": "media-object img-rounded",
        //             "src": comment.user.avatarUrl
        //         }));
        //
        //         var mediaBodyElement = $("<div/>", {
        //             "class": "media-body"
        //         }).append($("<h5/>", {
        //             "class": "sub-comment-media-heading",
        //             "html": comment.user.name
        //         })).append($("<div/>", {
        //             "html": comment.content
        //         })).append($("<div/>", {
        //             "class": "sub-comment-menu"
        //         }).append($("<span/>", {
        //             "class": "pull-right",
        //             "html": moment(comment.gmtCreate).format('YYYY-MM-DD')
        //         })));
        //
        //         var mediaElement = $("<div/>", {
        //             "class": "media"
        //         }).append(mediaLeftElement).append(mediaBodyElement).append($("<hr/>"));
        //
        //         var commentElement = $("<div/>", {
        //             "class": "col-lg-12 col-md-12 col-sm-12 col-xs-12 comments"
        //         }).append(mediaElement);
        //
        //         subCommentContainer.prepend(commentElement);
        //     });
        //     //展开二级评论
        //     comments.addClass("in");
        //     // 标记二级评论展开状态
        //     e.setAttribute("data-collapse", "in");
        //     e.classList.add("active");
        // });
        // }
    }
}

//HTML标签转义（< -> &lt;）
function html2Escape(sHtml) {
    return sHtml.replace(/[<>&"]/g, function (c) {
        return {'<': '&lt;', '>': '&gt;', '&': '&amp;', '"': '&quot;'}[c];
    });
}