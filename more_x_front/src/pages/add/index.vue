<template>
  <div>
    <div>收入支出值【计算器组件】</div>
    <div>分类【主分类和子分类】</div>
    <div>描述</div>
    <div>日期【日历表组件】</div>
    <div>支付方式</div>
    <calculator @res="getCalRes"></calculator>
  </div>
</template>

<script>
  import calculator from "../../components/add/calculator";
  export default {
    components: {
      calculator
    },
    methods: {
      getCalRes (res){
        console.log('get:' + res)
      }
    }
  }
</script>

<style lang="scss">

</style>
