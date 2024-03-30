import { createStore } from 'vuex'

const MEMBER = "MEMBER";

export default createStore({
  state: {
    // || {}避免空指针，设值，有值修改，无值存入
    member: window.SessionStorage.get(MEMBER) || {}
  },
  getters: {
  },
  mutations: {
    setMember(state,_member){
      state.member = _member;
      window.SessionStorage.set(MEMBER,_member);
    }
  },
  actions: {
  },
  modules: {
  }
})
