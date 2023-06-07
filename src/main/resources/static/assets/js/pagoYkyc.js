

const app = Vue.createApp({
    data() {
      return {
        email: "",
        contraseña: "",
        primerNombre: "",
        apellido: "",

      };
    },
  
    created() {

    },
    methods: {


//     desactivarBoton(){

//         localStorage.setItem("habilitado", "true")
//         window.location.href="/improvisado.html"
// },


  
      salida() {
        axios
          .post("/api/logout")
          .then((response) => console.log("signed out!!!"));
      },
 



    },
  }).mount("#app");
  


// FUNCION DE DISABBLE

//   function ocultar(id){
//     var elemento = document.getElementById(id);
//     elemento.style.display = "none";
// }