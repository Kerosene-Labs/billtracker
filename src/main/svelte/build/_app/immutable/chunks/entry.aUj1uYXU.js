var qt=t=>{throw TypeError(t)};var Pe=(t,e,n)=>e.has(t)||qt("Cannot "+n);var b=(t,e,n)=>(Pe(t,e,"read from private field"),n?n.call(t):e.get(t)),x=(t,e,n)=>e.has(t)?qt("Cannot add the same private member more than once"):e instanceof WeakSet?e.add(t):e.set(t,n);import{at as P,y as C,M as O,as as Ce}from"./runtime.CEeJOaW3.js";import{w as xt}from"./index.CHdXzry6.js";import{o as Ht}from"./index-client.C80z12Id.js";new URL("sveltekit-internal://");function Oe(t,e){return t==="/"||e==="ignore"?t:e==="never"?t.endsWith("/")?t.slice(0,-1):t:e==="always"&&!t.endsWith("/")?t+"/":t}function Ne(t){return t.split("%25").map(decodeURI).join("%25")}function je(t){for(const e in t)t[e]=decodeURIComponent(t[e]);return t}function St({href:t}){return t.split("#")[0]}const $e=["href","pathname","search","toString","toJSON"];function De(t,e,n){const r=new URL(t);Object.defineProperty(r,"searchParams",{value:new Proxy(r.searchParams,{get(a,o){if(o==="get"||o==="getAll"||o==="has")return s=>(n(s),a[o](s));e();const i=Reflect.get(a,o);return typeof i=="function"?i.bind(a):i}}),enumerable:!0,configurable:!0});for(const a of $e)Object.defineProperty(r,a,{get(){return e(),t[a]},enumerable:!0,configurable:!0});return r}const Fe="/__data.json",Ve=".html__data.json";function Be(t){return t.endsWith(".html")?t.replace(/\.html$/,Ve):t.replace(/\/$/,"")+Fe}function Me(...t){let e=5381;for(const n of t)if(typeof n=="string"){let r=n.length;for(;r;)e=e*33^n.charCodeAt(--r)}else if(ArrayBuffer.isView(n)){const r=new Uint8Array(n.buffer,n.byteOffset,n.byteLength);let a=r.length;for(;a;)e=e*33^r[--a]}else throw new TypeError("value must be a string or TypedArray");return(e>>>0).toString(36)}function Ge(t){const e=atob(t),n=new Uint8Array(e.length);for(let r=0;r<e.length;r++)n[r]=e.charCodeAt(r);return n.buffer}const se=window.fetch;window.fetch=(t,e)=>((t instanceof Request?t.method:(e==null?void 0:e.method)||"GET")!=="GET"&&K.delete(Pt(t)),se(t,e));const K=new Map;function qe(t,e){const n=Pt(t,e),r=document.querySelector(n);if(r!=null&&r.textContent){let{body:a,...o}=JSON.parse(r.textContent);const i=r.getAttribute("data-ttl");return i&&K.set(n,{body:a,init:o,ttl:1e3*Number(i)}),r.getAttribute("data-b64")!==null&&(a=Ge(a)),Promise.resolve(new Response(a,o))}return window.fetch(t,e)}function He(t,e,n){if(K.size>0){const r=Pt(t,n),a=K.get(r);if(a){if(performance.now()<a.ttl&&["default","force-cache","only-if-cached",void 0].includes(n==null?void 0:n.cache))return new Response(a.body,a.init);K.delete(r)}}return window.fetch(e,n)}function Pt(t,e){let r=`script[data-sveltekit-fetched][data-url=${JSON.stringify(t instanceof Request?t.url:t)}]`;if(e!=null&&e.headers||e!=null&&e.body){const a=[];e.headers&&a.push([...new Headers(e.headers)].join(",")),e.body&&(typeof e.body=="string"||ArrayBuffer.isView(e.body))&&a.push(e.body),r+=`[data-hash="${Me(...a)}"]`}return r}const Ke=/^(\[)?(\.\.\.)?(\w+)(?:=(\w+))?(\])?$/;function We(t){const e=[];return{pattern:t==="/"?/^\/$/:new RegExp(`^${Je(t).map(r=>{const a=/^\[\.\.\.(\w+)(?:=(\w+))?\]$/.exec(r);if(a)return e.push({name:a[1],matcher:a[2],optional:!1,rest:!0,chained:!0}),"(?:/(.*))?";const o=/^\[\[(\w+)(?:=(\w+))?\]\]$/.exec(r);if(o)return e.push({name:o[1],matcher:o[2],optional:!0,rest:!1,chained:!0}),"(?:/([^/]+))?";if(!r)return;const i=r.split(/\[(.+?)\](?!\])/);return"/"+i.map((c,f)=>{if(f%2){if(c.startsWith("x+"))return Et(String.fromCharCode(parseInt(c.slice(2),16)));if(c.startsWith("u+"))return Et(String.fromCharCode(...c.slice(2).split("-").map(l=>parseInt(l,16))));const d=Ke.exec(c),[,h,y,u,g]=d;return e.push({name:u,matcher:g,optional:!!h,rest:!!y,chained:y?f===1&&i[0]==="":!1}),y?"(.*?)":h?"([^/]*)?":"([^/]+?)"}return Et(c)}).join("")}).join("")}/?$`),params:e}}function Ye(t){return!/^\([^)]+\)$/.test(t)}function Je(t){return t.slice(1).split("/").filter(Ye)}function ze(t,e,n){const r={},a=t.slice(1),o=a.filter(s=>s!==void 0);let i=0;for(let s=0;s<e.length;s+=1){const c=e[s];let f=a[s-i];if(c.chained&&c.rest&&i&&(f=a.slice(s-i,s+1).filter(d=>d).join("/"),i=0),f===void 0){c.rest&&(r[c.name]="");continue}if(!c.matcher||n[c.matcher](f)){r[c.name]=f;const d=e[s+1],h=a[s+1];d&&!d.rest&&d.optional&&h&&c.chained&&(i=0),!d&&!h&&Object.keys(r).length===o.length&&(i=0);continue}if(c.optional&&c.chained){i++;continue}return}if(!i)return r}function Et(t){return t.normalize().replace(/[[\]]/g,"\\$&").replace(/%/g,"%25").replace(/\//g,"%2[Ff]").replace(/\?/g,"%3[Ff]").replace(/#/g,"%23").replace(/[.*+?^${}()|\\]/g,"\\$&")}function Xe({nodes:t,server_loads:e,dictionary:n,matchers:r}){const a=new Set(e);return Object.entries(n).map(([s,[c,f,d]])=>{const{pattern:h,params:y}=We(s),u={id:s,exec:g=>{const l=h.exec(g);if(l)return ze(l,y,r)},errors:[1,...d||[]].map(g=>t[g]),layouts:[0,...f||[]].map(i),leaf:o(c)};return u.errors.length=u.layouts.length=Math.max(u.errors.length,u.layouts.length),u});function o(s){const c=s<0;return c&&(s=~s),[c,t[s]]}function i(s){return s===void 0?s:[a.has(s),t[s]]}}function ie(t,e=JSON.parse){try{return e(sessionStorage[t])}catch{}}function Kt(t,e,n=JSON.stringify){const r=n(e);try{sessionStorage[t]=r}catch{}}var ee;const L=((ee=globalThis.__sveltekit_25sp6h)==null?void 0:ee.base)??"";var ne;const Ze=((ne=globalThis.__sveltekit_25sp6h)==null?void 0:ne.assets)??L,Qe="1734917984766",ce="sveltekit:snapshot",le="sveltekit:scroll",fe="sveltekit:states",tn="sveltekit:pageurl",M="sveltekit:history",Y="sveltekit:navigation",ft={tap:1,hover:2,viewport:3,eager:4,off:-1,false:-1},ct=location.origin;function ue(t){if(t instanceof URL)return t;let e=document.baseURI;if(!e){const n=document.getElementsByTagName("base");e=n.length?n[0].href:document.URL}return new URL(t,e)}function Ct(){return{x:pageXOffset,y:pageYOffset}}function V(t,e){return t.getAttribute(`data-sveltekit-${e}`)}const Wt={...ft,"":ft.hover};function de(t){let e=t.assignedSlot??t.parentNode;return(e==null?void 0:e.nodeType)===11&&(e=e.host),e}function he(t,e){for(;t&&t!==e;){if(t.nodeName.toUpperCase()==="A"&&t.hasAttribute("href"))return t;t=de(t)}}function Ut(t,e){let n;try{n=new URL(t instanceof SVGAElement?t.href.baseVal:t.href,document.baseURI)}catch{}const r=t instanceof SVGAElement?t.target.baseVal:t.target,a=!n||!!r||_t(n,e)||(t.getAttribute("rel")||"").split(/\s+/).includes("external"),o=(n==null?void 0:n.origin)===ct&&t.hasAttribute("download");return{url:n,external:a,target:r,download:o}}function ut(t){let e=null,n=null,r=null,a=null,o=null,i=null,s=t;for(;s&&s!==document.documentElement;)r===null&&(r=V(s,"preload-code")),a===null&&(a=V(s,"preload-data")),e===null&&(e=V(s,"keepfocus")),n===null&&(n=V(s,"noscroll")),o===null&&(o=V(s,"reload")),i===null&&(i=V(s,"replacestate")),s=de(s);function c(f){switch(f){case"":case"true":return!0;case"off":case"false":return!1;default:return}}return{preload_code:Wt[r??"off"],preload_data:Wt[a??"off"],keepfocus:c(e),noscroll:c(n),reload:c(o),replace_state:c(i)}}function Yt(t){const e=xt(t);let n=!0;function r(){n=!0,e.update(i=>i)}function a(i){n=!1,e.set(i)}function o(i){let s;return e.subscribe(c=>{(s===void 0||n&&c!==s)&&i(s=c)})}return{notify:r,set:a,subscribe:o}}const pe={v:()=>{}};function en(){const{set:t,subscribe:e}=xt(!1);let n;async function r(){clearTimeout(n);try{const a=await fetch(`${Ze}/_app/version.json`,{headers:{pragma:"no-cache","cache-control":"no-cache"}});if(!a.ok)return!1;const i=(await a.json()).version!==Qe;return i&&(t(!0),pe.v(),clearTimeout(n)),i}catch{return!1}}return{subscribe:e,check:r}}function _t(t,e){return t.origin!==ct||!t.pathname.startsWith(e)}function Jt(t){const e=rn(t),n=new ArrayBuffer(e.length),r=new DataView(n);for(let a=0;a<n.byteLength;a++)r.setUint8(a,e.charCodeAt(a));return n}const nn="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";function rn(t){t.length%4===0&&(t=t.replace(/==?$/,""));let e="",n=0,r=0;for(let a=0;a<t.length;a++)n<<=6,n|=nn.indexOf(t[a]),r+=6,r===24&&(e+=String.fromCharCode((n&16711680)>>16),e+=String.fromCharCode((n&65280)>>8),e+=String.fromCharCode(n&255),n=r=0);return r===12?(n>>=4,e+=String.fromCharCode(n)):r===18&&(n>>=2,e+=String.fromCharCode((n&65280)>>8),e+=String.fromCharCode(n&255)),e}const an=-1,on=-2,sn=-3,cn=-4,ln=-5,fn=-6;function un(t,e){if(typeof t=="number")return a(t,!0);if(!Array.isArray(t)||t.length===0)throw new Error("Invalid input");const n=t,r=Array(n.length);function a(o,i=!1){if(o===an)return;if(o===sn)return NaN;if(o===cn)return 1/0;if(o===ln)return-1/0;if(o===fn)return-0;if(i)throw new Error("Invalid input");if(o in r)return r[o];const s=n[o];if(!s||typeof s!="object")r[o]=s;else if(Array.isArray(s))if(typeof s[0]=="string"){const c=s[0],f=e==null?void 0:e[c];if(f)return r[o]=f(a(s[1]));switch(c){case"Date":r[o]=new Date(s[1]);break;case"Set":const d=new Set;r[o]=d;for(let u=1;u<s.length;u+=1)d.add(a(s[u]));break;case"Map":const h=new Map;r[o]=h;for(let u=1;u<s.length;u+=2)h.set(a(s[u]),a(s[u+1]));break;case"RegExp":r[o]=new RegExp(s[1],s[2]);break;case"Object":r[o]=Object(s[1]);break;case"BigInt":r[o]=BigInt(s[1]);break;case"null":const y=Object.create(null);r[o]=y;for(let u=1;u<s.length;u+=2)y[s[u]]=a(s[u+1]);break;case"Int8Array":case"Uint8Array":case"Uint8ClampedArray":case"Int16Array":case"Uint16Array":case"Int32Array":case"Uint32Array":case"Float32Array":case"Float64Array":case"BigInt64Array":case"BigUint64Array":{const u=globalThis[c],g=s[1],l=Jt(g),p=new u(l);r[o]=p;break}case"ArrayBuffer":{const u=s[1],g=Jt(u);r[o]=g;break}default:throw new Error(`Unknown type ${c}`)}}else{const c=new Array(s.length);r[o]=c;for(let f=0;f<s.length;f+=1){const d=s[f];d!==on&&(c[f]=a(d))}}else{const c={};r[o]=c;for(const f in s){const d=s[f];c[f]=a(d)}}return r[o]}return a(0)}const ge=new Set(["load","prerender","csr","ssr","trailingSlash","config"]);[...ge];const dn=new Set([...ge]);[...dn];function hn(t){return t.filter(e=>e!=null)}class wt{constructor(e,n){this.status=e,typeof n=="string"?this.body={message:n}:n?this.body=n:this.body={message:`Error: ${e}`}}toString(){return JSON.stringify(this.body)}}class me{constructor(e,n){this.status=e,this.location=n}}class Ot extends Error{constructor(e,n,r){super(r),this.status=e,this.text=n}}const pn="x-sveltekit-invalidated",gn="x-sveltekit-trailing-slash";function dt(t){return t instanceof wt||t instanceof Ot?t.status:500}function mn(t){return t instanceof Ot?t.text:"Internal Error"}let A,J,Rt;const yn=Ht.toString().includes("$$")||/function \w+\(\) \{\}/.test(Ht.toString());var Z,Q,tt,et,nt,rt,at,ot,re,st,ae,it,oe;yn?(A={data:{},form:null,error:null,params:{},route:{id:null},state:{},status:-1,url:new URL("https://example.com")},J={current:null},Rt={current:!1}):(A=new(re=class{constructor(){x(this,Z,P({}));x(this,Q,P(null));x(this,tt,P(null));x(this,et,P({}));x(this,nt,P({id:null}));x(this,rt,P({}));x(this,at,P(-1));x(this,ot,P(new URL("https://example.com")))}get data(){return C(b(this,Z))}set data(e){O(b(this,Z),e)}get form(){return C(b(this,Q))}set form(e){O(b(this,Q),e)}get error(){return C(b(this,tt))}set error(e){O(b(this,tt),e)}get params(){return C(b(this,et))}set params(e){O(b(this,et),e)}get route(){return C(b(this,nt))}set route(e){O(b(this,nt),e)}get state(){return C(b(this,rt))}set state(e){O(b(this,rt),e)}get status(){return C(b(this,at))}set status(e){O(b(this,at),e)}get url(){return C(b(this,ot))}set url(e){O(b(this,ot),e)}},Z=new WeakMap,Q=new WeakMap,tt=new WeakMap,et=new WeakMap,nt=new WeakMap,rt=new WeakMap,at=new WeakMap,ot=new WeakMap,re),J=new(ae=class{constructor(){x(this,st,P(null))}get current(){return C(b(this,st))}set current(e){O(b(this,st),e)}},st=new WeakMap,ae),Rt=new(oe=class{constructor(){x(this,it,P(!1))}get current(){return C(b(this,it))}set current(e){O(b(this,it),e)}},it=new WeakMap,oe),pe.v=()=>Rt.current=!0);function _n(t){Object.assign(A,t)}const wn=new Set(["icon","shortcut icon","apple-touch-icon"]),D=ie(le)??{},z=ie(ce)??{},j={url:Yt({}),page:Yt({}),navigating:xt(null),updated:en()};function Nt(t){D[t]=Ct()}function vn(t,e){let n=t+1;for(;D[n];)delete D[n],n+=1;for(n=e+1;z[n];)delete z[n],n+=1}function q(t){return location.href=t.href,new Promise(()=>{})}async function ye(){if("serviceWorker"in navigator){const t=await navigator.serviceWorker.getRegistration(L||"/");t&&await t.update()}}function zt(){}let vt,Tt,ht,N,Lt,F;const _e=[],pt=[];let U=null;const we=[],bn=[];let B=[],_={branch:[],error:null,url:null},jt=!1,gt=!1,Xt=!0,X=!1,H=!1,ve=!1,$t=!1,Dt,I,T,mt;const W=new Set;async function Dn(t,e,n){var a,o,i,s;document.URL!==location.href&&(location.href=location.href),F=t,await((o=(a=t.hooks).init)==null?void 0:o.call(a)),vt=Xe(t),N=document.documentElement,Lt=e,Tt=t.nodes[0],ht=t.nodes[1],Tt(),ht(),I=(i=history.state)==null?void 0:i[M],T=(s=history.state)==null?void 0:s[Y],I||(I=T=Date.now(),history.replaceState({...history.state,[M]:I,[Y]:T},""));const r=D[I];r&&(history.scrollRestoration="manual",scrollTo(r.x,r.y)),n?await Tn(Lt,n):In(location.href,{replaceState:!0}),Un()}function An(){_e.length=0,$t=!1}function be(t){pt.some(e=>e==null?void 0:e.snapshot)&&(z[t]=pt.map(e=>{var n;return(n=e==null?void 0:e.snapshot)==null?void 0:n.capture()}))}function Ae(t){var e;(e=z[t])==null||e.forEach((n,r)=>{var a,o;(o=(a=pt[r])==null?void 0:a.snapshot)==null||o.restore(n)})}function Zt(){Nt(I),Kt(le,D),be(T),Kt(ce,z)}async function ke(t,e,n,r){return lt({type:"goto",url:ue(t),keepfocus:e.keepFocus,noscroll:e.noScroll,replace_state:e.replaceState,state:e.state,redirect_count:n,nav_token:r,accept:()=>{e.invalidateAll&&($t=!0)}})}async function kn(t){if(t.id!==(U==null?void 0:U.id)){const e={};W.add(e),U={id:t.id,token:e,promise:Ee({...t,preload:e}).then(n=>(W.delete(e),n.type==="loaded"&&n.state.error&&(U=null),n))}}return U.promise}async function It(t){const e=vt.find(n=>n.exec(Re(t)));e&&await Promise.all([...e.layouts,e.leaf].map(n=>n==null?void 0:n[1]()))}function Se(t,e,n){var o;_=t.state;const r=document.querySelector("style[data-sveltekit]");r&&r.remove(),Object.assign(A,t.props.page),Dt=new F.root({target:e,props:{...t.props,stores:j,components:pt},hydrate:n,sync:!1}),Ae(T);const a={from:null,to:{params:_.params,route:{id:((o=_.route)==null?void 0:o.id)??null},url:new URL(location.href)},willUnload:!1,type:"enter",complete:Promise.resolve()};B.forEach(i=>i(a)),gt=!0}function yt({url:t,params:e,branch:n,status:r,error:a,route:o,form:i}){let s="never";if(L&&(t.pathname===L||t.pathname===L+"/"))s="always";else for(const u of n)(u==null?void 0:u.slash)!==void 0&&(s=u.slash);t.pathname=Oe(t.pathname,s),t.search=t.search;const c={type:"loaded",state:{url:t,params:e,branch:n,error:a,route:o},props:{constructors:hn(n).map(u=>u.node.component),page:A}};i!==void 0&&(c.props.form=i);let f={},d=!A,h=0;for(let u=0;u<Math.max(n.length,_.branch.length);u+=1){const g=n[u],l=_.branch[u];(g==null?void 0:g.data)!==(l==null?void 0:l.data)&&(d=!0),g&&(f={...f,...g.data},d&&(c.props[`data_${h}`]=f),h+=1)}return(!_.url||t.href!==_.url.href||_.error!==a||i!==void 0&&i!==A.form||d)&&(c.props.page={error:a,params:e,route:{id:(o==null?void 0:o.id)??null},state:{},status:r,url:new URL(t),form:i??null,data:d?f:A.data}),c}async function Ft({loader:t,parent:e,url:n,params:r,route:a,server_data_node:o}){var d,h,y;let i=null,s=!0;const c={dependencies:new Set,params:new Set,parent:!1,route:!1,url:!1,search_params:new Set},f=await t();if((d=f.universal)!=null&&d.load){let u=function(...l){for(const p of l){const{href:k}=new URL(p,n);c.dependencies.add(k)}};const g={route:new Proxy(a,{get:(l,p)=>(s&&(c.route=!0),l[p])}),params:new Proxy(r,{get:(l,p)=>(s&&c.params.add(p),l[p])}),data:(o==null?void 0:o.data)??null,url:De(n,()=>{s&&(c.url=!0)},l=>{s&&c.search_params.add(l)}),async fetch(l,p){let k;l instanceof Request?(k=l.url,p={body:l.method==="GET"||l.method==="HEAD"?void 0:await l.blob(),cache:l.cache,credentials:l.credentials,headers:[...l.headers].length?l.headers:void 0,integrity:l.integrity,keepalive:l.keepalive,method:l.method,mode:l.mode,redirect:l.redirect,referrer:l.referrer,referrerPolicy:l.referrerPolicy,signal:l.signal,...p}):k=l;const E=new URL(k,n);return s&&u(E.href),E.origin===n.origin&&(k=E.href.slice(n.origin.length)),gt?He(k,E.href,p):qe(k,p)},setHeaders:()=>{},depends:u,parent(){return s&&(c.parent=!0),e()},untrack(l){s=!1;try{return l()}finally{s=!0}}};i=await f.universal.load.call(null,g)??null}return{node:f,loader:t,server:o,universal:(h=f.universal)!=null&&h.load?{type:"data",data:i,uses:c}:null,data:i??(o==null?void 0:o.data)??null,slash:((y=f.universal)==null?void 0:y.trailingSlash)??(o==null?void 0:o.slash)}}function Qt(t,e,n,r,a,o){if($t)return!0;if(!a)return!1;if(a.parent&&t||a.route&&e||a.url&&n)return!0;for(const i of a.search_params)if(r.has(i))return!0;for(const i of a.params)if(o[i]!==_.params[i])return!0;for(const i of a.dependencies)if(_e.some(s=>s(new URL(i))))return!0;return!1}function Vt(t,e){return(t==null?void 0:t.type)==="data"?t:(t==null?void 0:t.type)==="skip"?e??null:null}function Sn(t,e){if(!t)return new Set(e.searchParams.keys());const n=new Set([...t.searchParams.keys(),...e.searchParams.keys()]);for(const r of n){const a=t.searchParams.getAll(r),o=e.searchParams.getAll(r);a.every(i=>o.includes(i))&&o.every(i=>a.includes(i))&&n.delete(r)}return n}function te({error:t,url:e,route:n,params:r}){return{type:"loaded",state:{error:t,url:e,route:n,params:r,branch:[]},props:{page:A,constructors:[]}}}async function Ee({id:t,invalidating:e,url:n,params:r,route:a,preload:o}){if((U==null?void 0:U.id)===t)return W.delete(U.token),U.promise;const{errors:i,layouts:s,leaf:c}=a,f=[...s,c];i.forEach(m=>m==null?void 0:m().catch(()=>{})),f.forEach(m=>m==null?void 0:m[1]().catch(()=>{}));let d=null;const h=_.url?t!==_.url.pathname+_.url.search:!1,y=_.route?a.id!==_.route.id:!1,u=Sn(_.url,n);let g=!1;const l=f.map((m,v)=>{var $;const S=_.branch[v],R=!!(m!=null&&m[0])&&((S==null?void 0:S.loader)!==m[1]||Qt(g,y,h,u,($=S.server)==null?void 0:$.uses,r));return R&&(g=!0),R});if(l.some(Boolean)){try{d=await Te(n,l)}catch(m){const v=await G(m,{url:n,params:r,route:{id:t}});return W.has(o)?te({error:v,url:n,params:r,route:a}):bt({status:dt(m),error:v,url:n,route:a})}if(d.type==="redirect")return d}const p=d==null?void 0:d.nodes;let k=!1;const E=f.map(async(m,v)=>{var At;if(!m)return;const S=_.branch[v],R=p==null?void 0:p[v];if((!R||R.type==="skip")&&m[1]===(S==null?void 0:S.loader)&&!Qt(k,y,h,u,(At=S.universal)==null?void 0:At.uses,r))return S;if(k=!0,(R==null?void 0:R.type)==="error")throw R;return Ft({loader:m[1],url:n,params:r,route:a,parent:async()=>{var Gt;const Mt={};for(let kt=0;kt<v;kt+=1)Object.assign(Mt,(Gt=await E[kt])==null?void 0:Gt.data);return Mt},server_data_node:Vt(R===void 0&&m[0]?{type:"skip"}:R??null,m[0]?S==null?void 0:S.server:void 0)})});for(const m of E)m.catch(()=>{});const w=[];for(let m=0;m<f.length;m+=1)if(f[m])try{w.push(await E[m])}catch(v){if(v instanceof me)return{type:"redirect",location:v.location};if(W.has(o))return te({error:await G(v,{params:r,url:n,route:{id:a.id}}),url:n,params:r,route:a});let S=dt(v),R;if(p!=null&&p.includes(v))S=v.status??S,R=v.error;else if(v instanceof wt)R=v.body;else{if(await j.updated.check())return await ye(),await q(n);R=await G(v,{params:r,url:n,route:{id:a.id}})}const $=await En(m,w,i);return $?yt({url:n,params:r,branch:w.slice(0,$.idx).concat($.node),status:S,error:R,route:a}):await Ue(n,{id:a.id},R,S)}else w.push(void 0);return yt({url:n,params:r,branch:w,status:200,error:null,route:a,form:e?void 0:null})}async function En(t,e,n){for(;t--;)if(n[t]){let r=t;for(;!e[r];)r-=1;try{return{idx:r+1,node:{node:await n[t](),loader:n[t],data:{},server:null,universal:null}}}catch{continue}}}async function bt({status:t,error:e,url:n,route:r}){const a={};let o=null;if(F.server_loads[0]===0)try{const f=await Te(n,[!0]);if(f.type!=="data"||f.nodes[0]&&f.nodes[0].type!=="data")throw 0;o=f.nodes[0]??null}catch{(n.origin!==ct||n.pathname!==location.pathname||jt)&&await q(n)}const s=await Ft({loader:Tt,url:n,params:a,route:r,parent:()=>Promise.resolve({}),server_data_node:Vt(o)}),c={node:await ht(),loader:ht,universal:null,server:null,data:null};return yt({url:n,params:a,branch:[s,c],status:t,error:e,route:null})}function Bt(t,e){if(!t||_t(t,L))return;let n;try{n=F.hooks.reroute({url:new URL(t)})??t.pathname}catch{return}const r=Re(n);for(const a of vt){const o=a.exec(r);if(o)return{id:t.pathname+t.search,invalidating:e,route:a,params:je(o),url:t}}}function Re(t){return Ne(t.slice(L.length)||"/")}function Ie({url:t,type:e,intent:n,delta:r}){let a=!1;const o=xe(_,n,t,e);r!==void 0&&(o.navigation.delta=r);const i={...o.navigation,cancel:()=>{a=!0,o.reject(new Error("navigation cancelled"))}};return X||we.forEach(s=>s(i)),a?null:o}async function lt({type:t,url:e,popped:n,keepfocus:r,noscroll:a,replace_state:o,state:i={},redirect_count:s=0,nav_token:c={},accept:f=zt,block:d=zt}){const h=Bt(e,!1),y=Ie({url:e,type:t,delta:n==null?void 0:n.delta,intent:h});if(!y){d();return}const u=I,g=T;f(),X=!0,gt&&j.navigating.set(J.current=y.navigation),mt=c;let l=h&&await Ee(h);if(!l){if(_t(e,L))return await q(e);l=await Ue(e,{id:null},await G(new Ot(404,"Not Found",`Not found: ${e.pathname}`),{url:e,params:{},route:{id:null}}),404)}if(e=(h==null?void 0:h.url)||e,mt!==c)return y.reject(new Error("navigation aborted")),!1;if(l.type==="redirect")if(s>=20)l=await bt({status:500,error:await G(new Error("Redirect loop"),{url:e,params:{},route:{id:null}}),url:e,route:{id:null}});else return ke(new URL(l.location,e).href,{},s+1,c),!1;else l.props.page.status>=400&&await j.updated.check()&&(await ye(),await q(e));if(An(),Nt(u),be(g),l.props.page.url.pathname!==e.pathname&&(e.pathname=l.props.page.url.pathname),i=n?n.state:i,!n){const w=o?0:1,m={[M]:I+=w,[Y]:T+=w,[fe]:i};(o?history.replaceState:history.pushState).call(history,m,"",e),o||vn(I,T)}if(U=null,l.props.page.state=i,gt){_=l.state,l.props.page&&(l.props.page.url=e);const w=(await Promise.all(bn.map(m=>m(y.navigation)))).filter(m=>typeof m=="function");if(w.length>0){let m=function(){B=B.filter(v=>!w.includes(v))};w.push(m),B.push(...w)}Dt.$set(l.props),_n(l.props.page),ve=!0}else Se(l,Lt,!1);const{activeElement:p}=document;await Ce();const k=n?n.scroll:a?Ct():null;if(Xt){const w=e.hash&&document.getElementById(decodeURIComponent(e.hash.slice(1)));k?scrollTo(k.x,k.y):w?w.scrollIntoView():scrollTo(0,0)}const E=document.activeElement!==p&&document.activeElement!==document.body;!r&&!E&&Ln(),Xt=!0,l.props.page&&Object.assign(A,l.props.page),X=!1,t==="popstate"&&Ae(T),y.fulfil(void 0),B.forEach(w=>w(y.navigation)),j.navigating.set(J.current=null)}async function Ue(t,e,n,r){return t.origin===ct&&t.pathname===location.pathname&&!jt?await bt({status:r,error:n,url:t,route:e}):await q(t)}function Rn(){let t;N.addEventListener("mousemove",o=>{const i=o.target;clearTimeout(t),t=setTimeout(()=>{r(i,2)},20)});function e(o){o.defaultPrevented||r(o.composedPath()[0],1)}N.addEventListener("mousedown",e),N.addEventListener("touchstart",e,{passive:!0});const n=new IntersectionObserver(o=>{for(const i of o)i.isIntersecting&&(It(i.target.href),n.unobserve(i.target))},{threshold:0});function r(o,i){const s=he(o,N);if(!s)return;const{url:c,external:f,download:d}=Ut(s,L);if(f||d)return;const h=ut(s),y=c&&_.url.pathname+_.url.search===c.pathname+c.search;if(!h.reload&&!y)if(i<=h.preload_data){const u=Bt(c,!1);u&&kn(u)}else i<=h.preload_code&&It(c.pathname)}function a(){n.disconnect();for(const o of N.querySelectorAll("a")){const{url:i,external:s,download:c}=Ut(o,L);if(s||c)continue;const f=ut(o);f.reload||(f.preload_code===ft.viewport&&n.observe(o),f.preload_code===ft.eager&&It(i.pathname))}}B.push(a),a()}function G(t,e){if(t instanceof wt)return t.body;const n=dt(t),r=mn(t);return F.hooks.handleError({error:t,event:e,status:n,message:r})??{message:r}}function In(t,e={}){return t=ue(t),t.origin!==ct?Promise.reject(new Error("goto: invalid URL")):ke(t,e,0)}function Un(){var e;history.scrollRestoration="manual",addEventListener("beforeunload",n=>{let r=!1;if(Zt(),!X){const a=xe(_,void 0,null,"leave"),o={...a.navigation,cancel:()=>{r=!0,a.reject(new Error("navigation cancelled"))}};we.forEach(i=>i(o))}r?(n.preventDefault(),n.returnValue=""):history.scrollRestoration="auto"}),addEventListener("visibilitychange",()=>{document.visibilityState==="hidden"&&Zt()}),(e=navigator.connection)!=null&&e.saveData||Rn(),N.addEventListener("click",async n=>{if(n.button||n.which!==1||n.metaKey||n.ctrlKey||n.shiftKey||n.altKey||n.defaultPrevented)return;const r=he(n.composedPath()[0],N);if(!r)return;const{url:a,external:o,target:i,download:s}=Ut(r,L);if(!a)return;if(i==="_parent"||i==="_top"){if(window.parent!==window)return}else if(i&&i!=="_self")return;const c=ut(r);if(!(r instanceof SVGAElement)&&a.protocol!==location.protocol&&!(a.protocol==="https:"||a.protocol==="http:")||s)return;const[d,h]=a.href.split("#"),y=d===St(location);if(o||c.reload&&(!y||!h)){Ie({url:a,type:"link"})?X=!0:n.preventDefault();return}if(h!==void 0&&y){const[,u]=_.url.href.split("#");if(u===h){if(n.preventDefault(),h===""||h==="top"&&r.ownerDocument.getElementById("top")===null)window.scrollTo({top:0});else{const g=r.ownerDocument.getElementById(decodeURIComponent(h));g&&(g.scrollIntoView(),g.focus())}return}if(H=!0,Nt(I),t(a),!c.replace_state)return;H=!1}n.preventDefault(),await new Promise(u=>{requestAnimationFrame(()=>{setTimeout(u,0)}),setTimeout(u,100)}),lt({type:"link",url:a,keepfocus:c.keepfocus,noscroll:c.noscroll,replace_state:c.replace_state??a.href===location.href})}),N.addEventListener("submit",n=>{if(n.defaultPrevented)return;const r=HTMLFormElement.prototype.cloneNode.call(n.target),a=n.submitter;if(((a==null?void 0:a.formTarget)||r.target)==="_blank"||((a==null?void 0:a.formMethod)||r.method)!=="get")return;const s=new URL((a==null?void 0:a.hasAttribute("formaction"))&&(a==null?void 0:a.formAction)||r.action);if(_t(s,L))return;const c=n.target,f=ut(c);if(f.reload)return;n.preventDefault(),n.stopPropagation();const d=new FormData(c),h=a==null?void 0:a.getAttribute("name");h&&d.append(h,(a==null?void 0:a.getAttribute("value"))??""),s.search=new URLSearchParams(d).toString(),lt({type:"form",url:s,keepfocus:f.keepfocus,noscroll:f.noscroll,replace_state:f.replace_state??s.href===location.href})}),addEventListener("popstate",async n=>{var r;if((r=n.state)!=null&&r[M]){const a=n.state[M];if(mt={},a===I)return;const o=D[a],i=n.state[fe]??{},s=new URL(n.state[tn]??location.href),c=n.state[Y],f=St(location)===St(_.url);if(c===T&&(ve||f)){t(s),D[I]=Ct(),o&&scrollTo(o.x,o.y),i!==A.state&&(A.state=i,Dt.$set({page:A})),I=a;return}const h=a-I;await lt({type:"popstate",url:s,popped:{state:i,scroll:o,delta:h},accept:()=>{I=a,T=c},block:()=>{history.go(-h)},nav_token:mt})}else if(!H){const a=new URL(location.href);t(a)}}),addEventListener("hashchange",()=>{H&&(H=!1,history.replaceState({...history.state,[M]:++I,[Y]:T},"",location.href))});for(const n of document.querySelectorAll("link"))wn.has(n.rel)&&(n.href=n.href);addEventListener("pageshow",n=>{n.persisted&&j.navigating.set(J.current=null)});function t(n){_.url=A.url=n,j.page.set({data:A.data,error:A.error,form:A.form,params:A.params,route:A.route,state:A.state,status:A.status,url:n}),j.page.notify()}}async function Tn(t,{status:e=200,error:n,node_ids:r,params:a,route:o,data:i,form:s}){jt=!0;const c=new URL(location.href);({params:a={},route:o={id:null}}=Bt(c,!1)||{});let f,d=!0;try{const h=r.map(async(g,l)=>{const p=i[l];return p!=null&&p.uses&&(p.uses=Le(p.uses)),Ft({loader:F.nodes[g],url:c,params:a,route:o,parent:async()=>{const k={};for(let E=0;E<l;E+=1)Object.assign(k,(await h[E]).data);return k},server_data_node:Vt(p)})}),y=await Promise.all(h),u=vt.find(({id:g})=>g===o.id);if(u){const g=u.layouts;for(let l=0;l<g.length;l++)g[l]||y.splice(l,0,void 0)}f=yt({url:c,params:a,branch:y,status:e,error:n,form:s,route:u??null})}catch(h){if(h instanceof me){await q(new URL(h.location,location.href));return}f=await bt({status:dt(h),error:await G(h,{url:c,params:a,route:o}),url:c,route:o}),t.textContent="",d=!1}f.props.page&&(f.props.page.state={}),Se(f,t,d)}async function Te(t,e){var a;const n=new URL(t);n.pathname=Be(t.pathname),t.pathname.endsWith("/")&&n.searchParams.append(gn,"1"),n.searchParams.append(pn,e.map(o=>o?"1":"0").join(""));const r=await se(n.href);if(!r.ok){let o;throw(a=r.headers.get("content-type"))!=null&&a.includes("application/json")?o=await r.json():r.status===404?o="Not Found":r.status===500&&(o="Internal Error"),new wt(r.status,o)}return new Promise(async o=>{var h;const i=new Map,s=r.body.getReader(),c=new TextDecoder;function f(y){return un(y,{...F.decoders,Promise:u=>new Promise((g,l)=>{i.set(u,{fulfil:g,reject:l})})})}let d="";for(;;){const{done:y,value:u}=await s.read();if(y&&!d)break;for(d+=!u&&d?`
`:c.decode(u,{stream:!0});;){const g=d.indexOf(`
`);if(g===-1)break;const l=JSON.parse(d.slice(0,g));if(d=d.slice(g+1),l.type==="redirect")return o(l);if(l.type==="data")(h=l.nodes)==null||h.forEach(p=>{(p==null?void 0:p.type)==="data"&&(p.uses=Le(p.uses),p.data=f(p.data))}),o(l);else if(l.type==="chunk"){const{id:p,data:k,error:E}=l,w=i.get(p);i.delete(p),E?w.reject(f(E)):w.fulfil(f(k))}}}})}function Le(t){return{dependencies:new Set((t==null?void 0:t.dependencies)??[]),params:new Set((t==null?void 0:t.params)??[]),parent:!!(t!=null&&t.parent),route:!!(t!=null&&t.route),url:!!(t!=null&&t.url),search_params:new Set((t==null?void 0:t.search_params)??[])}}function Ln(){const t=document.querySelector("[autofocus]");if(t)t.focus();else{const e=document.body,n=e.getAttribute("tabindex");e.tabIndex=-1,e.focus({preventScroll:!0,focusVisible:!1}),n!==null?e.setAttribute("tabindex",n):e.removeAttribute("tabindex");const r=getSelection();if(r&&r.type!=="None"){const a=[];for(let o=0;o<r.rangeCount;o+=1)a.push(r.getRangeAt(o));setTimeout(()=>{if(r.rangeCount===a.length){for(let o=0;o<r.rangeCount;o+=1){const i=a[o],s=r.getRangeAt(o);if(i.commonAncestorContainer!==s.commonAncestorContainer||i.startContainer!==s.startContainer||i.endContainer!==s.endContainer||i.startOffset!==s.startOffset||i.endOffset!==s.endOffset)return}r.removeAllRanges()}})}}}function xe(t,e,n,r){var c,f;let a,o;const i=new Promise((d,h)=>{a=d,o=h});return i.catch(()=>{}),{navigation:{from:{params:t.params,route:{id:((c=t.route)==null?void 0:c.id)??null},url:t.url},to:n&&{params:(e==null?void 0:e.params)??null,route:{id:((f=e==null?void 0:e.route)==null?void 0:f.id)??null},url:n},willUnload:!e,type:r,complete:i},fulfil:a,reject:o}}export{Dn as a,In as g,A as p,j as s,Rt as u};
