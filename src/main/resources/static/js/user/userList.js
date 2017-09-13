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
        createPerson: function () {
            this.people.push(this.newPerson);
            this.newPerson = {
                name: '',
                age: 0,
                sex: 'Male'
            }
        },
        deletePerson: function (index) {
            // 删一个数组元素
            this.people.splice(index, 1)
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