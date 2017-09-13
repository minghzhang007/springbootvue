var VM;

VM = new Vue({
    el: "#app",
    data: {
        newPerson: {
            name: '',
            age: 0,
            sex: 'Male'
        },
        people: [
            {
                name: 'Jack',
                age: 30,
                sex: 'Male'
            }, {
                name: 'Bill',
                age: 26,
                sex: 'Male'
            }, {
                name: 'Tracy',
                age: 22,
                sex: 'Female'
            }, {
                name: 'Chris',
                age: 36,
                sex: 'Male'
            }
        ],
        name:""
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
            $.ajax({
                url:"http://localhost:8060/sayHello",
                type:"POST",
                dataType:"JSON",
                success:function (data) {
                    console.log("返回数据："+data);
                    alert("返回数据："+data);
                    _this.name=data;
                }
            });
        }
    }
});