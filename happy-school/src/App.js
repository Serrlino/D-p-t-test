import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Routes,
  Route,
} from "react-router-dom";

// import * as Page from './Pages';
import Navbar from './Components/Navbar';

// importons toutes les pages un a un alors  ....
import EnseignantForm from './Pages/EnseignantForm';
import EnseignantList from './Pages/EnseignantList';
import SectionForm from './Pages/SectionForm';
import SectionList from './Pages/SectionList';
import CycleList from './Pages/CycleList';
import  CycleForm from './Pages/CycleForm';
import SerieList from './Pages/SerieList';
import  SerieForm from './Pages/SerieForm';
import FonctionList from './Pages/FonctionList';
import  FonctionForm from './Pages/FonctionForm';
import GradeList from './Pages/GradeList';
import  GradeForm from './Pages/GradeForm';
import MatiereList from './Pages/MatiereList';
import  MatiereForm from './Pages/MatiereForm';
import BureauList from './Pages/BureauList';
import  BureauForm from './Pages/BureauForm';
import Index from './Pages/Index';


function App() {
  return (
    <Router>
      <Navbar />
      <Routes>

        <Route exact path='/' element={<Index/>} />
        
          {/**  Chemin vers le composant Enseignant */}
          <Route exact path="ens" element={<EnseignantForm />} />
          <Route path="/ensList" element={<EnseignantList />} />

          {/**  Chemin vers le composant grade */}
          <Route path="/grade" element={<GradeForm />} />
          <Route path="/gradeList" element={<GradeList />} />

          {/**  Chemin vers le composant  section*/}
          <Route path="/section" element={<SectionForm />} />
          {<Route path="/sectionList" element={<SectionList />} /> }

          {/**  Chemin vers le composant matiere */}
          <Route path="/matiere" element={<MatiereForm />} />
          <Route path="/matiereList" element={<MatiereList />} />

          {/**  Chemin vers le composant  serie*/}
          <Route path="/serie" element={<SerieForm />} />
          <Route path="/serieList" element={<SerieList />} />

          {/**  Chemin vers le composant cycle */}
          <Route path="/cycle" element={<CycleForm />} />
          <Route path="/cycleList" element={<CycleList />} />

          {/**  Chemin vers le composant  fonction*/}
          <Route path="/fonction" element={<FonctionForm />} />
          <Route path="/fonctionList" element={<FonctionList />} />

          {/**  Chemin vers le composant  bureau*/}
          <Route path="/bureau" element={<BureauForm />} />
          <Route path="/bureauList" element={<BureauList />} />
        
      </Routes>
    </Router>
  );
}

export default App;
