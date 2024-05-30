import React, { useState } from "react";
import ActionFonc from '../Components/ActionFonc';

function FonctionForm(){

  // let[fonction, setFonction] = useState([])
  let [fonctionName, setFonctionName] = useState('')
  let [description, setDescription] = useState('')

  let addFonction = async (event) =>
    {
      event.preventDefault();

      let data = await fetch('http://localhost:8081/fonction/create/' + fonctionName +'/'+ description, {method : 'post'}).then(response => {
        if(!response.ok){
          throw new Error('Erreur lors de la création de la fonction');
        }
        return response.text();
      }).then(data => {
        alert(data);
      }).catch(error =>{
        alert(error.message)
      });
    }

    return(
        <div className="content">
  <h2>Gerer la Fonction</h2>
  <hr/>
  <div className="row">

      <ActionFonc/>

    <div className="column2">
      <h2>Ajouter une fonction </h2>
        <p> Servez vous de ce formulaire pour  renseigner les fonctions que comporte votre etablissement </p>

  <div className="container">
    <form action="#">
      <div className="row">
        <div className="col-25">
          <label for="fname">Nom </label>
        </div>
        <div className="col-75">
          <input type="text" id="fname" name="firstname" placeholder="Libelle de la fonction .." value={fonctionName} onChange={(e) => {setFonctionName(e.target.value)}}/>
        </div>
      </div>

      <div className="row">
        <div className="col-25">
          <label for="subject">Description</label>
        </div>
        <div className="col-75">
          <textarea id="subject" name="subject" placeholder="Description de la fonction .."  style={{ height:200 + 'px' }} value={description} onChange={(e) => {setDescription(e.target.value)}} ></textarea>
        </div>
      </div>

      <div className="row">
        <button value="Submit" width="10" onClick={addFonction}> Enregistrer </button>
      </div> 
    </form>
</div>
</div>
            </div>
        </div> 
    );
}

export default FonctionForm;