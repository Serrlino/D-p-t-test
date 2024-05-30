import React, {useEffect, useState} from "react";
import ActionMat from '../Components/ActionMat';

function MatiereForm(){

  // const [materialData, setMaterialData] = useState({
  //   code:'',
  let [gradeId, setGradeId] = useState()
  let [registerNumber, setRegisterNumber] = useState()


    let [code, setCode] = useState('')
    let [materialName, setMaterialName] = useState('')
    let [materialCoef, setMaterialCoef] = useState()
    let [grade, setGrade] = useState()
    let [teacher, setTeacher] = useState()


  let addMaterial = (e) =>
    {
      let path = code + '/' + materialName + '/' + materialCoef
      + '/' + gradeId + '/' + registerNumber

      e.preventDefault();
      fetch('http://localhost:8081/material/create/' + path, { method: 'post'}).then(response => {return response.text()}).then(data => {
        alert('Cours crée avec succès', data);
      }).catch(error =>{
        alert('Erreur lors de la création de la matere ' + error);
      });
    }

    let [teachers, setTeachers] = useState([]);

    let findTeacher = () =>
      {
        fetch('http://localhost:8081/teacher/index').then(response => response.json()).then(data => setTeachers(data)).catch(error => alert('Erreur de connexion a la base', error));
      }
  
    let [grades, setGrades] = useState([]);
  
    let findGrade = () =>
      {
        fetch('http://localhost:8081/grade/read').then(response => response.json()).then(data => setGrades(data)).catch(error => alert('>Erreur de connexion a la base de donnees', error));
      }

      let changeGrade = (event) =>
        {
          let val = event.target.value
          setGrade(val)
          let d = val.split(':')
          d = parseInt(d[0])
          setGradeId(d)
        }

        let changeTeach = (event) =>
          {
            let val = event.target.value
            setTeacher(val)
            let d = val.split(':')
            d = parseInt(d[0])
            setRegisterNumber(d)
          }
           console.log( code + '/' + materialName + '/' + materialCoef
            + '/' + gradeId + '/' + registerNumber)

    
      return(
        <div className="content">
  <h2>Gerer la matiere</h2>
  <hr/>
  <div className="row">

    <ActionMat/>

    <div className="column2">
      <h2>Ajouter une matiere </h2>
        <p> Servez vous de ce formulaire pour  renseigner les matieres que comporte votre etablissement </p>

  <div className="container">
    <form>

      <div className="row">
        <div className="col-25">
          <label for="fname">Code </label>
        </div>
        <div className="col-75">
          <input type="text" id="_code" name="code" value={code} placeholder="code de la matiere .." onChange={(e) => {
            setCode(e.target.value)
          }} />
        </div>
      </div>

      <div className="row">
        <div className="col-25">
          <label for="fname">Nom </label>
        </div>
        <div className="col-75">
          <input type="text" id="fname" name="firstname" value={materialName} placeholder="Libelle de la matiere .." onChange={(e) => {
            setMaterialName(e.target.value)
          }}/>
        </div>
      </div>

      <div className="row">
        <div className="col-25">
          <label for="fname">coefficient </label>
        </div>
        <div className="col-75">
          <input type="text" id="_coef" name="coef" value={materialCoef} placeholder="coefficient de la matiere .." onChange={(e) => {
            setMaterialCoef(e.target.value)
          }}/>
        </div>
      </div>

      <div className="row">
        <div className="col-25">
          <label for="fname">Enseignant </label>
        </div>
        <div className="col-75">
          <select value={teacher} onChange={changeTeach} onClick={findTeacher}>
            {/* <option value="">Enseignant Selectionné</option> */}
            {teachers.map(teacherI=> (
              <option>
                {teacherI.registerNumber}:{teacherI.teacherName}
                </option>
            ))}
          </select>
        </div>
      </div>

      <div className="row">
        <div className="col-25">
          <label for="fname">Classe </label>
        </div>
        <div className="col-75">
          <select value={grade} onChange={changeGrade} onClick={findGrade}  >
            {/* <option value="">Classe Selectionnée</option> */}
            {grades.map(gradeItem => (
              <option>
                {gradeItem.gradeId}:{gradeItem.gradeName}
                </option>
            ))}
          </select>
        </div>
      </div>

      <div className="row">
        <button value="Submit" width="10"  onClick={addMaterial}> Enregistrer </button>

      </div> 
    </form>
</div>
</div>
</div>
    </div>

    );
  }

export default MatiereForm;