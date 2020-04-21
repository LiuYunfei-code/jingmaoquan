// 表单非空校验
function check(e) {
    let value = e.value;
    if (value == null || value === "") {
        let label = e.label;
        alert("此项不能为空！请输入内容");
    }
}