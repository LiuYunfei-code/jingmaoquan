// 表单非空校验
function check(e) {
    let value = e.value;
    if (value == null || value === "") {
        let label = e.label;
        alert("此项不能为空！请输入内容");
    }
}

function questionComment() {

    let parentId = $(" #parentId ").val();
    let parentType=1;
    let commentContent=$(" #comment-content ").val();
    let type=1;

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
            "parentType":parentType
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