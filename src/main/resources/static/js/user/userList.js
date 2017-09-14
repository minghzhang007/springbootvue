var VM;

VM = new Vue({
    el: "#app",
    data: {
        people: [ ],
    },
    mounted:function () {
        this.$nextTick(function () {
            this.query();
        })
    }
    ,
    methods: {
        createUser: function () {
            var _this = this;
            var url="http://localhost:8060/addUsers";
            $.ajax({
                url:url,
                type:"GET",
                dataType:"JSON",
                success:function (data) {
                    console.log("返回数据："+data);
                    //_this.people=data;
                    _this.query();
                }
            });
        },

        query:function () {
            var _this = this;
            var data = $("#searchForm").serializeArray()
            $.ajax({
                url:"http://localhost:8060/user",
                type:"POST",
                dataType:"JSON",
                data:data,
                success:function (data) {
                    console.log("返回数据："+data);
                    _this.people=data;
                }
            });
        }
    }
});