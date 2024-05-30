import React, { useState } from "react";
import ActionSer from '../Components/ActionSer';

function SerieForm(){

  // let[series, setSeries] = useState([])
  let [seriesName, setSeriesName] = useState('')

  let addSeries = async (event) =>
    {
      event.preventDefault();
      let data = await fetch('http://localhost:8081/series/create/' + seriesName, {method : 'post'}).then(response => {
        if(!response.ok){
          throw new Error('Erreur lors de la création de la série');
        }
        return response.text();
      }).then(data => {
        alert(data);
      }).catch(error => {
        alert(error.message);
      });
    }

    return(
<div className="content">
  <h2>Gerer la Serie</h2>
  <hr/>
  <div className="row">

    <ActionSer/>

    <div className="column2">
      <h2>Ajouter une serie </h2>
        <p> Servez vous de ce formulaire pour  renseigner les series que comporte votre etablissement </p>

  <div className="container">
    <form action="#">
      <div className="row">
        <div className="col-25">
          <label for="fname">Nom </label>
        </div>
        <div className="col-75">
          <input type="text" id="fname" name="firstname" placeholder="Libelle de la serie .." value={seriesName} onChange={(e) => {setSeriesName(e.target.value)}}/>
        </div>
      </div>

      <div className="row">
        <button value="Submit" width="10"  onClick={addSeries}> Enregistrer </button>

      </div> 
    </form>
</div>
</div>
            </div>
        </div> 
    );
}

export default SerieForm;