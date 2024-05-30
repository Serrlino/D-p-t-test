import React, { useState } from "react";
import ActionBur from '../Components/ActionBur';

function BureauForm(){

  let [officeName, setOfficeName] = useState('')

  let addOffice = async (event) =>
    {
      event.preventDefault();
      
      let data = await fetch('http://localhost:8081/office/create/' + officeName, {method : 'post'}).then(response => {
        if(!response.ok){
          throw new Error('Erreur lors de la création du bureau');
        }
        return response.text();
      }).then(data => {
        alert(data);
      }).catch(error =>{
        alert(error.message);
      });
    }

    return(
        <div className="content">
  <h2>Gerer le Bureau</h2>
  <hr/>
  <div className="row">

  <ActionBur/>

    <div className="column2">
      <h2>Ajouter un bureau </h2>
        <p> Servez vous de ce formulaire pour  renseigner les bureaux que comporte votre etablissement </p>

  <div className="container">
    <form action="#">
      <div className="row">
        <div className="col-25">
          <label for="fname">Nom </label>
        </div>
        <div className="col-75">
          <input type="text" id="fname" name="firstname" placeholder="Libelle de la bureau .."  value={officeName} onChange={(e) => {setOfficeName(e.target.value)}}/>
        </div>        
        
      </div>

      {/* <div className="row">
        <div className="col-25">
          <label for="subject">Description</label>
        </div>
        <div className="col-75">
          <textarea id="subject" name="subject" placeholder="Description du bureau .."  style={{ height:200 + 'px' }}></textarea>
        </div>
      </div> */}

      <div className="row">
        <button value="Submit" width="10" onClick={addOffice}> Enregistrer </button>
      </div> 
    </form>
</div>
</div>
            </div>
        </div> 
    );
}

export default BureauForm;