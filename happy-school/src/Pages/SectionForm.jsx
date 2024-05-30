import React, { useState } from "react";
import ActionSection from '../Components/ActionSection';

function SectionForm(){

  

    // let[section, setSection] = useState([])
    let [sectionName, setSectionName] = useState('')
  
    let addSection = async (event) =>
      {
        event.preventDefault();

        let data = await fetch('http://localhost:8081/section/create/' + sectionName, {method : 'post'}).then(response => {
          if(!response.ok){
            throw new Error('Erreur lors de la création de la section');
          }
          
          return  response.text();
        }).then(data => {
          alert(data);
        }).catch(error => {
          alert(error);
        });
      }
  
    return(
        <div className="content">
            <h2>Gerer la Section</h2>
            <hr/>
            <div className="row">

                <ActionSection/>

                <div className="column2">
                    <h2>Ajouter une section </h2>
                    <p> Servez vous de ce formulaire pour  renseigner les sections que comporte votre etablissement </p>

                    <div className="container">
                        <form action="#">
                        <div className="row">
                            <div className="col-25">
                            <label for="fname">Nom </label>
                            </div>
                            <div className="col-75">
                            <input type="text" id="fname" name="firstname" placeholder="Libelle de la section .." value={sectionName} onChange={(e) => {setSectionName(e.target.value)}}/>
                            </div>
                        </div>

                        <div className="row">
                            <button value="Submit" width="10" onClick={addSection}> Enregistrer </button>
                        </div> 
                        </form>
                    </div>
                </div>
            </div>
        </div>    
    );
}

export default SectionForm;