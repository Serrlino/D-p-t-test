import React, {useState, useEffect} from "react";
import ActionCyc from '../Components/ActionCyc';

function CycleForm(){

  let[Cycle, setCycle] = useState([])
  let [CycleName, setCycleName] = useState('')

  let addCycle = async (event) =>
    {
      event.preventDefault();
      let data = await fetch('http://localhost:8081/Cycle/create/' + CycleName, {method : 'post'}).then(response => {
        if(!response.ok){
          throw new Error('Erreur lors de la création du produit');
        }
        return response.text();
      }).then(data => {
        alert(data);
      }).catch(error =>{
        alert(error);
      });
    }


    return(
<div className="content">
  <h2>Gerer la Cycle</h2>
  <hr/>
  <div className="row">

      <ActionCyc/>

    <div className="column2">
      <h2>Ajouter un Cycle </h2>
        <p> Servez vous de ce formulaire pour  renseigner les differents cycles de l'etablissement </p>

  <div className="container">
    <form action="#">
      <div className="row">
        <div className="col-25">
          <label for="fname">Nom </label>
        </div>
        <div className="col-75">
          <input type="text" id="fname" name="firstname" placeholder="Libelle du cycle a ajouter.."/>
        </div>
      </div>

      <div className="row">
        <div className="col-25">
          <label for="country">Section</label>
        </div>
        <div className="col-75">
          <select id="country" name="country">
            <option value="australia">Section1</option>
            <option value="canada">Section2</option>
            <option value="usa">Section3</option>
            <option value="usa">Section4</option>
            <option value="usa">Section5</option>
            <option value="usa">Section6</option>
            <option value="usa">Section7</option>
            <option value="usa">Section8</option>
            <option value="usa">Section9</option>
            <option value="usa">Section10</option>
          </select>
        </div>
      </div>

      <div className="row">
        <button value="Submit" width="10"> Enregistrer </button>

      </div> 
    </form>
</div>
</div>
            </div>
        </div> 
    );
}

export default CycleForm;