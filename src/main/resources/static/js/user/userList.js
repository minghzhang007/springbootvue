var VM;

VM = new Vue({
    el: "#app",
    data: {
        people: [],
        pageSize: 10,
        // 搜索表单
        queryForm: {},
        addForm: {},
        // 结果对象
        result: {
            data: [],
            paginator: {
                currentPage: 0,
                totalCount: 0,
                totalPage: 0
            }
        },
    },
    mounted: function () {
        this.$nextTick(function () {
            this.query();
        })
    }
    ,
    methods: {
        createUser: function () {
            var _this = this;
            var url = "http://localhost:8060/addUser";
            var data = _this.addForm;
            $.ajax({
                url: url,
                type: "POST",
                dataType: "JSON",
                data: data,
                success: function (data) {
                    console.log("返回数据：" + data);
                    _this.query();
                }
            });
        },

        query: function () {
            var _this = this;
            var pageInfo = {
                "currentPage": _this.result.paginator.currentPage,
                "pageSize": _this.pageSize,
                "totalCount": _this.result.paginator.totalCount
            };
            $.ajax({
                url: "http://localhost:8060/userPage",
                type: "POST",
                dataType: "JSON",
                data: $.extend(_this.queryForm, pageInfo),
                success: function (data) {
                    console.log("返回数据：" + data);
                    _this.result = data;
                }
            });
        },
        queryAll: function () {
            var _this = this;
            $.ajax({
                url: "http://localhost:8060/allUser",
                type: "POST",
                dataType: "JSON",
                data: _this.queryForm,
                success: function (data) {
                    console.log("返回数据：" + data);
                    _this.result.data = data;
                }
            });
        },
        exportToExcel: function () {
            window.location.href = "http://localhost:8060/export";
        }
    }
});

$('.addUser').click(function () {
    var url = "http://localhost:8060/addUser";

    var name = $("#addName").val();
    var hobby = $("#addHobby").val();
    var jsonString = {
        "name": name,
        "hobby": hobby
    };
    window.location.href="http://localhost:8060/addUser?name="+name+"&hobby="+hobby;
    /*$.ajax({
        url: url,
        type: "POST",
        dataType: "JSON",
        data:jsonString,
        success: function (data) {
            console.log("返回数据：" + data);
            _this.query();
        }
    });*/
});