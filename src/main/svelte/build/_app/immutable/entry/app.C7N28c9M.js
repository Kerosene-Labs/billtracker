const __vite__mapDeps=(i,m=__vite__mapDeps,d=(m.f||(m.f=["../nodes/0.DbV67Lyl.js","../chunks/disclose-version.DZcaPsiO.js","../chunks/runtime.CEeJOaW3.js","../chunks/legacy.ByRZ0RZV.js","../chunks/props.DoochoKG.js","../chunks/toast.vWwVVyZE.js","../chunks/index.CHdXzry6.js","../chunks/render.CrzR9ip1.js","../chunks/class.Bvena8yX.js","../chunks/lifecycle.Debgd6PA.js","../chunks/index-client.C80z12Id.js","../assets/0.DmxE1RUG.css","../nodes/1.D9reqkrB.js","../chunks/entry.aUj1uYXU.js","../nodes/2.4aZbYr94.js","../chunks/slot.CJIVWpca.js","../chunks/BaseLayout.xPiSy4nR.js","../nodes/3.CivFoxiu.js","../nodes/4.4aZbYr94.js","../nodes/5.BzN8u_Vw.js","../chunks/Button.CbQACl4x.js","../chunks/if.DKbmQBJX.js","../assets/Button.Bkq-HqAQ.css","../chunks/Card.B7bBLkGU.js","../assets/Card.VwBYa1g2.css","../nodes/6.KBXBRZUe.js","../chunks/IntroductionsCard.TunBVSfS.js","../chunks/sdkUtil.CEYXJ9Be.js","../assets/IntroductionsCard.ZeE214FY.css","../nodes/7.B4rdHTZ-.js","../nodes/8.ClRVjlfj.js"])))=>i.map(i=>d[i]);
var X=e=>{throw TypeError(e)};var Z=(e,t,r)=>t.has(e)||X("Cannot "+r);var c=(e,t,r)=>(Z(e,t,"read from private field"),r?r.call(e):t.get(e)),W=(e,t,r)=>t.has(e)?X("Cannot add the same private member more than once"):t instanceof WeakSet?t.add(e):t.set(e,r),z=(e,t,r,i)=>(Z(e,t,"write to private field"),i?i.call(e,r):t.set(e,r),r);import{h as p,g as ct,e as ut,E as lt,m as ft,q as dt,o as mt,ap as _t,aq as ht,w as vt,ad as gt,S as Et,y as h,a0 as yt,M as U,ar as Pt,ae as Rt,T as bt,p as Ot,u as Tt,t as At,as as Lt,f as T,a as kt,at as H,s as wt,c as xt,r as St,D as Dt,B as I}from"../chunks/runtime.CEeJOaW3.js";import{h as It,m as Vt,u as Ct,s as jt}from"../chunks/render.CrzR9ip1.js";import{a as E,t as et,c as w,b as qt}from"../chunks/disclose-version.DZcaPsiO.js";import{i as B}from"../chunks/if.DKbmQBJX.js";import{p as F,a as Bt}from"../chunks/props.DoochoKG.js";import{o as Ft}from"../chunks/index-client.C80z12Id.js";function V(e,t,r){p&&ct();var i=e,n,o;ut(()=>{n!==(n=t())&&(o&&(mt(o),o=null),n&&(o=ft(()=>r(i,n))))},lt),p&&(i=dt)}function $(e,t){return e===t||(e==null?void 0:e[Et])===t}function C(e={},t,r,i){return _t(()=>{var n,o;return ht(()=>{n=o,o=[],vt(()=>{e!==r(...o)&&(t(e,...o),n&&$(r(...n),e)&&t(null,...n))})}),()=>{gt(()=>{o&&$(r(...o),e)&&t(null,...o)})}}),e}function Ut(e){return class extends Yt{constructor(t){super({component:e,...t})}}}var P,f;class Yt{constructor(t){W(this,P);W(this,f);var o;var r=new Map,i=(a,s)=>{var u=bt(s);return r.set(a,u),u};const n=new Proxy({...t.props||{},$$events:{}},{get(a,s){return h(r.get(s)??i(s,Reflect.get(a,s)))},has(a,s){return s===yt?!0:(h(r.get(s)??i(s,Reflect.get(a,s))),Reflect.has(a,s))},set(a,s,u){return U(r.get(s)??i(s,u),u),Reflect.set(a,s,u)}});z(this,f,(t.hydrate?It:Vt)(t.component,{target:t.target,anchor:t.anchor,props:n,context:t.context,intro:t.intro??!1,recover:t.recover})),(!((o=t==null?void 0:t.props)!=null&&o.$$host)||t.sync===!1)&&Pt(),z(this,P,n.$$events);for(const a of Object.keys(c(this,f)))a==="$set"||a==="$destroy"||a==="$on"||Rt(this,a,{get(){return c(this,f)[a]},set(s){c(this,f)[a]=s},enumerable:!0});c(this,f).$set=a=>{Object.assign(n,a)},c(this,f).$destroy=()=>{Ct(c(this,f))}}$set(t){c(this,f).$set(t)}$on(t,r){c(this,P)[t]=c(this,P)[t]||[];const i=(...n)=>r.call(this,...n);return c(this,P)[t].push(i),()=>{c(this,P)[t]=c(this,P)[t].filter(n=>n!==i)}}$destroy(){c(this,f).$destroy()}}P=new WeakMap,f=new WeakMap;const Gt="modulepreload",Mt=function(e,t){return new URL(e,t).href},tt={},y=function(t,r,i){let n=Promise.resolve();if(r&&r.length>0){const a=document.getElementsByTagName("link"),s=document.querySelector("meta[property=csp-nonce]"),u=(s==null?void 0:s.nonce)||(s==null?void 0:s.getAttribute("nonce"));n=Promise.allSettled(r.map(d=>{if(d=Mt(d,i),d in tt)return;tt[d]=!0;const R=d.endsWith(".css"),j=R?'[rel="stylesheet"]':"";if(!!i)for(let b=a.length-1;b>=0;b--){const A=a[b];if(A.href===d&&(!R||A.rel==="stylesheet"))return}else if(document.querySelector(`link[href="${d}"]${j}`))return;const v=document.createElement("link");if(v.rel=R?"stylesheet":Gt,R||(v.as="script"),v.crossOrigin="",v.href=d,u&&v.setAttribute("nonce",u),document.head.appendChild(v),R)return new Promise((b,A)=>{v.addEventListener("load",b),v.addEventListener("error",()=>A(new Error(`Unable to preload CSS for ${d}`)))})}))}function o(a){const s=new Event("vite:preloadError",{cancelable:!0});if(s.payload=a,window.dispatchEvent(s),!s.defaultPrevented)throw a}return n.then(a=>{for(const s of a||[])s.status==="rejected"&&o(s.reason);return t().catch(o)})},se={};var Nt=et('<div id="svelte-announcer" aria-live="assertive" aria-atomic="true" style="position: absolute; left: 0; top: 0; clip: rect(0 0 0 0); clip-path: inset(50%); overflow: hidden; white-space: nowrap; width: 1px; height: 1px"><!></div>'),Wt=et("<!> <!>",1);function zt(e,t){Ot(t,!0);let r=F(t,"components",23,()=>[]),i=F(t,"data_0",3,null),n=F(t,"data_1",3,null),o=F(t,"data_2",3,null);Tt(()=>t.stores.page.set(t.page)),At(()=>{t.stores,t.page,t.constructors,r(),t.form,i(),n(),o(),t.stores.page.notify()});let a=H(!1),s=H(!1),u=H(null);Ft(()=>{const l=t.stores.page.subscribe(()=>{h(a)&&(U(s,!0),Lt().then(()=>{U(u,Bt(document.title||"untitled page"))}))});return U(a,!0),l});const d=I(()=>t.constructors[2]);var R=Wt(),j=T(R);{var J=l=>{var g=w();const x=I(()=>t.constructors[0]);var S=T(g);V(S,()=>h(x),(O,L)=>{C(L(O,{get data(){return i()},get form(){return t.form},children:(m,Kt)=>{var K=w(),rt=T(K);{var st=k=>{var D=w();const Y=I(()=>t.constructors[1]);var G=T(D);V(G,()=>h(Y),(M,N)=>{C(N(M,{get data(){return n()},get form(){return t.form},children:(_,Qt)=>{var Q=w(),nt=T(Q);V(nt,()=>h(d),(ot,it)=>{C(it(ot,{get data(){return o()},get form(){return t.form}}),q=>r()[2]=q,()=>{var q;return(q=r())==null?void 0:q[2]})}),E(_,Q)},$$slots:{default:!0}}),_=>r()[1]=_,()=>{var _;return(_=r())==null?void 0:_[1]})}),E(k,D)},at=k=>{var D=w();const Y=I(()=>t.constructors[1]);var G=T(D);V(G,()=>h(Y),(M,N)=>{C(N(M,{get data(){return n()},get form(){return t.form}}),_=>r()[1]=_,()=>{var _;return(_=r())==null?void 0:_[1]})}),E(k,D)};B(rt,k=>{t.constructors[2]?k(st):k(at,!1)})}E(m,K)},$$slots:{default:!0}}),m=>r()[0]=m,()=>{var m;return(m=r())==null?void 0:m[0]})}),E(l,g)},v=l=>{var g=w();const x=I(()=>t.constructors[0]);var S=T(g);V(S,()=>h(x),(O,L)=>{C(L(O,{get data(){return i()},get form(){return t.form}}),m=>r()[0]=m,()=>{var m;return(m=r())==null?void 0:m[0]})}),E(l,g)};B(j,l=>{t.constructors[1]?l(J):l(v,!1)})}var b=wt(j,2);{var A=l=>{var g=Nt(),x=xt(g);{var S=O=>{var L=qt();Dt(()=>jt(L,h(u))),E(O,L)};B(x,O=>{h(s)&&O(S)})}St(g),E(l,g)};B(b,l=>{h(a)&&l(A)})}E(e,R),kt()}const ae=Ut(zt),ne=[()=>y(()=>import("../nodes/0.DbV67Lyl.js"),__vite__mapDeps([0,1,2,3,4,5,6,7,8,9,10,11]),import.meta.url),()=>y(()=>import("../nodes/1.D9reqkrB.js"),__vite__mapDeps([12,1,2,3,7,9,13,6,10]),import.meta.url),()=>y(()=>import("../nodes/2.4aZbYr94.js"),__vite__mapDeps([14,1,2,3,15,16,8,4]),import.meta.url),()=>y(()=>import("../nodes/3.CivFoxiu.js"),__vite__mapDeps([17,1,2,3,15,16,8,4]),import.meta.url),()=>y(()=>import("../nodes/4.4aZbYr94.js"),__vite__mapDeps([18,1,2,3,15,16,8,4]),import.meta.url),()=>y(()=>import("../nodes/5.BzN8u_Vw.js"),__vite__mapDeps([19,1,2,3,9,20,7,21,15,4,22,23,24]),import.meta.url),()=>y(()=>import("../nodes/6.KBXBRZUe.js"),__vite__mapDeps([25,1,2,3,9,23,7,21,15,4,24,20,22,13,6,10,26,27,5,28]),import.meta.url),()=>y(()=>import("../nodes/7.B4rdHTZ-.js"),__vite__mapDeps([29,1,2,3,7,21,9,10,23,15,4,24,27,13,6]),import.meta.url),()=>y(()=>import("../nodes/8.ClRVjlfj.js"),__vite__mapDeps([30,1,2,3,23,7,21,15,4,24,26,9,10,20,22,27,5,6,13,28]),import.meta.url)],oe=[],ie={"/":[5],"/home":[6,[2]],"/oauth/google":[7,[3]],"/settings":[8,[4]]},Ht={handleError:({error:e})=>{console.error(e)},reroute:()=>{},transport:{}},Jt=Object.fromEntries(Object.entries(Ht.transport).map(([e,t])=>[e,t.decode])),ce=(e,t)=>Jt[e](t);export{ce as decode,Jt as decoders,ie as dictionary,Ht as hooks,se as matchers,ne as nodes,ae as root,oe as server_loads};
