<template>
  <div>
    <picker-view indicator-style="height: 50px;" class="picker-view"  @change="bindChange">
      <picker-view-column v-for="(col,colIndex) in cols" :key="colIndex">
        <view v-for="(item, index) in col" :key="index"  class="picker-view-col" >{{item.name}}</view>
      </picker-view-column>

    </picker-view>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        colCount: 2,
        cols:[],
      }
    },
    methods: {
      bindChange(e) {
        this.$emit('res', e.target.value)
      }
    }
  }
</script>

<style lang="scss">

</style>
