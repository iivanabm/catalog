import React from 'react';
import './styles.scss';
import { ReactComponent as MainImage } from '../../core/assets/images/main-image.svg';

const Home = () => (
  <div className="home-container">
    <div className="row home-content">
      <div className="col-6">
        <h1 className="text-title">
          The best products catalog
        </h1>
        <p className="text-subtitle">
          We help you to find the best products online.
        </p>
      </div>
      <div className="col-6">
        <MainImage className="main-image" />
      </div>
    </div>
  </div>
);

export default Home;