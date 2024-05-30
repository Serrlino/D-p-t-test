
import React, { useState } from "react";
import ActionSer from '../Components/ActionSer';

function SerieList() {
    const [isOpen, setIsOpen] = useState(false);

    const toggleModal = () => {
        setIsOpen(!isOpen);
    };


    let [seriess, setSeriess] = useState([])
    let [code, setCode] = useState('')
  
    let findSeries = () =>
      {
        fetch('http://localhost:8081/series/index').then(response => {
          if(!response.ok){
            throw new Error('Erreur lors de la création de la series');
          }
          return response.json();
        }).then(data => {
            setSeriess(data) 
        }).catch(error =>{
          alert(error.message)
          });
      }
      findSeries()
    //   console.log(seriess)

    let deleteSeries = (delCode) =>
        {
            fetch('http://localhost:8081/series/delete/' + delCode, {method : 'delete'}).then(response => {
                if(!response.ok){
                  throw new Error('Erreur lors de la création de la series');
                }
                return response.json();
              }).then(data => { 
                  alert('Serie supprimé')
              }).catch(error =>{
                alert(error.message)
            });
      
        }

  return (
    <div className="content">
        <h2>Gerer les Series</h2>
        <hr/>
        <div className="row">

            <ActionSer/>
            {/* Corp du modal pour la modification */}
            <div>
                
                {isOpen && (
                    <div className="modal-overlay">
                        <div className="modal-content"> 
                        <div className="modal-close" onClick={toggleModal}> &times; </div>
                            <h2>Modification</h2> 
                            <p>Modifier les informations de la serie.</p>
                            <div className="row">
                            <div className="col-25">
          <label for="fname">Nom </label>
        </div>
        <div className="col-75">
          <input type="text" id="fname" name="firstname" placeholder="Libelle de la serie .." />
          {/* value={seriesName} onChange={(e) => {setSeriesName(e.target.value)}} */}
        </div>
                            </div>
                            
                            <div className="modal-footer">
                                <button onClick={toggleModal}>Valider</button>
                            </div>
                        </div>
            </div>
            // fin du corp du modal
            
      )}
    </div>
            <div className="column2">
                <h2>Liste des Series </h2>
                <p> Le tableau suivant contient les Series enregistres dans l'etablissement</p>

                <div className="container">
                    <table>
                    <tr>
                            <th>Code</th>
                            <th>Nom</th>
                            <th>Actions</th>
                        </tr>

                            {seriess.map((series) => { 
                              
                                return(
                                <tr>
                                    <td>{series.code}</td>
                                    <td>{series.seriesName}</td>
                                    <td>
                                    <button onClick={toggleModal} className="btn default">Mettre a jour</button>
                                <button onClick={() => {
                                             deleteSeries(series.code)}} className="btn danger">Supprimer</button>

                                    </td>
                                </tr>)
                            })}
                    </table>
                </div>
            </div>
        </div>
    </div>
  );
}

export default SerieList;
