import React, { useState } from "react";
import ActionGrade from '../Components/ActionGrade';

function GradeForm(){

    // let[grade, setGrade] = useState([])
  let [gradeName, setGradeName] = useState('')
  let [inventory, setInventory] = useState()

  let addGrade = async (event) =>
    {
      event.preventDefault();
      let data = await fetch('http://localhost:8081/grade/create/' + gradeName + '/' + inventory, {method : 'post'}).then(response => {
        if(!response.ok){
          throw new Error('Erreur lors de la création de la classe');
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
            <h2>Gerer la Classe </h2>
            <hr/>
            <div className="row">

                <ActionGrade/>

                <div className="column2">
                    <h2>Ajouter une classe </h2>
                    <p> Servez vous de ce formulaire pour  renseigner les classes que comporte votre etablissement </p>

                    <div className="container">
                        <form action="#">
                            <div className="row">
                                <div className="col-25">
                                    <label for="fname">Nom </label>
                                </div>
                                <div class="col-75">
                                    <input type="text" id="fname" name="firstname" placeholder="Libelle du grade .." value={gradeName} onChange={(e) => {setGradeName(e.target.value)}}/>
                                </div>

                                <div className="col-25">
                                <label for="finventory">Effictif </label>
                                </div>
                                
                                <div className="col-75">
                                    <input type="text" id="finventory" name="firstname" placeholder="Effectif .."  value={inventory} onChange={(e) => {setInventory(e.target.value)}}/>
                                </div>

                            </div>

                        {/* <div className="row">
                            <div className="col-25">
                                <label for="subject">Description</label>
                            </div>
                            <div className="col-75">
                                <textarea id="subject" name="subject" placeholder="Description du grade .."  style={{ height:200 + 'px' }}></textarea>
                            </div>
                        </div> */}

                        <div className="row">
                            <button value="Submit" width="10" onClick={addGrade}> Enregistrer </button>
                        </div> 
                        </form>
                    </div>
                </div>
            </div>       
        </div>
    );
}

export default GradeForm;