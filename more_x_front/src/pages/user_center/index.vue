<template>
  <div>
    <div class="avatar-container">
      <img :src="currentUser.avatarUrl" alt="">
      <div>{{currentUser.nickName}}</div>
    </div>

    <div class="user-options">
        <div class="left-msg">用户子页面</div>
        <div class="right-msg"> > </div>
    </div>

    <div class="user-options">
      <div class="left-msg">用户子页面</div>
      <div class="right-msg"> > </div>
    </div>

    <div class="user-options">
      <div class="left-msg">用户子页面</div>
      <div class="right-msg"> > </div>
    </div>


    <button class="logout" @click="logout">退出</button>



  </div>
</template>

<script>
  export default {
    data() {
      return {
        currentUser : {
          avatarUrl: '',
          nickName: ''
        }
      }
    },

    mounted() {
      if(wx.getStorageSync('userinfo')){
        console.log('进入用户中心')
        this.currentUser = wx.getStorageSync('userinfo')
      }
      else {
        console.log('异常登录进入')
      }

    },

    methods: {
      logout () {
        wx.removeStorage({
          key: 'userinfo',
          success () {
            wx.hideTabBar()

            wx.showToast({
              title: '退出登录',
              icon: 'success',
              duration: 1000
            })

            wx.reLaunch({
              url: '/pages/index/main?logout=true'
            })

          }
        })
      }
    }

  }
</script>

<style lang="scss">
.avatar-container {
  background-color: #322f3b;
  padding: 10px;
  img {
    width: 100px;
    height: 100px;
    border-radius: 99px;
    box-shadow: 1px 1px 2px #322f3b;
    border: 1px solid #e2e1e4;
  }
  div {
    display: inline-block;
    color: #e2e1e4;
  }
}
.user-options{
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
  border-radius: 10px;
  box-shadow: 1px 1px 2px #322f3b;
  border: 1px solid #e2e1e4;
  display: flex;
  justify-content: space-between;
  font-weight: lighter;
  .left-msg {
    height: 45px;
    font-size: 20px;
    padding-left: 15px;
    line-height: 45px;
  }
  .right-msg {
    height: 45px;
    font-size: 20px;
    padding-right: 15px;
    line-height: 45px;
  }


}
.logout {
  height: 45px;
  line-height: 45px;
  font-size: 20px;
  font-weight: bolder;
  margin-top: 10px;
  margin-left: 10px;
  margin-right: 10px;
  border-radius: 10px;
  box-shadow: 1px 1px 2px #322f3b;
  border: 1px solid #e2e1e4;
  justify-content: space-between;
  background-color: crimson;
  color: #e2e1e4;
  text-align: center;
}
</style>
